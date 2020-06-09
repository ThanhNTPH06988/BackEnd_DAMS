package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.*;
import com.tamdt.pmbackend.business.dtos.UsersDialogGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersSearchDTO;
import com.tamdt.pmbackend.business.repositories.*;
import com.tamdt.pmbackend.business.services.AdminUsersService;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import com.tamdt.pmbackend.common.ulti.DateTimeUltils;
import com.tamdt.pmbackend.common.ulti.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class AdminUsersServiceImpl extends BasicServiceImpl<AdminUsersDomain> implements AdminUsersService {

	@Autowired
	private AdminUsersRepository repo;

	@Autowired
	private HisLoginRepository hisLoginRepo;

	@Autowired
	private AdminMenuRepository roleRepo;

	@Autowired
	private AdminUserRoleRepository auRoleRepo;

	@Autowired
	private AdminStaffRepository staffRepo;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public BasicRepository<AdminUsersDomain> getRepository() {
		return repo;
	}

	@Override
	@Transactional
	public AdminUsersDomain getUserInfo(String username, String password) {

		AdminUsersDomain usersDomain = repo.findByUserNameAndDeleted(username, AppConstant.DELETED.NO);
		if (usersDomain != null && passEncoder.matches(password, usersDomain.getPassword())) {

			//update login status
			repo.updateLoginStatus(usersDomain.getId(),usersDomain.getNumberLogin() + 1L);

			//save his
			HisLoginDomain hisLogin = new HisLoginDomain();
			hisLogin.setUserId(usersDomain.getId());
			hisLogin.setUserName(username);
			hisLogin.setDateLogin(new Date());
			hisLoginRepo.save(hisLogin);


			return usersDomain;
		}
		return new AdminUsersDomain();
	}

	@Override
	public ListJson<AdminUsersDomain> searchUsers(UsersSearchDTO searchDTO) {

		Long countSearch = repo.countSearchUsers(searchDTO,IContext.getUserNameLogin());
		List<AdminUsersDomain> lstRs = new ArrayList<>();

		if(countSearch > 0L){
			lstRs = repo.searchUsers(searchDTO,IContext.getUserNameLogin());
		}

		return new ListJson<AdminUsersDomain>(lstRs,(long)countSearch);
	}

	@Override
	public UsersRolesGrantDTO getUsersForRoles(Long userId) {
		UsersRolesGrantDTO roles = new UsersRolesGrantDTO();

		List<AdminRolesDomain> lstNoGrant = roleRepo.getUsersRoleNoGrant(userId);
		roles.setLstNoGrant(lstNoGrant);
		if(userId != null && userId>0L){
			List<AdminRolesDomain> lstGrant = roleRepo.getUsersRoleGrant(userId);
			roles.setLstGrant(lstGrant);
		}

		return roles;
	}

	@Override
	public void deletedUsers(Long userId) {
		repo.deletedUsers(userId);
	}

	@Override
	@Transactional
	public void grantRoleForUser(UsersDialogGrantDTO roleGrantDTO) {
		AdminUsersDomain userLogin = repo.findByUserNameAndDeleted(IContext.getUserNameLogin(),AppConstant.DELETED.NO);
		//delete befor update
		auRoleRepo.deleteByUserId(roleGrantDTO.getUserId());

		//update
		for(AdminRolesDomain role : roleGrantDTO.getLstRoles()){
			AdminUserRoleDomain aURole = new AdminUserRoleDomain();
			aURole.setUserId(roleGrantDTO.getUserId());
			aURole.setRoleId(role.getId());
			aURole.setDateCreated(new Date());
			aURole.setCreateBy(userLogin.getId());
			auRoleRepo.save(aURole);
		}
	}

	@Override
	@Transactional
	public void lockOrUnlock(Long userId, Long type) {
		repo.lockOrUnlock(userId,type);
	}

	@Override
	public void forgotPass(String email) {

		AdminUsersDomain userDomain = repo.getByEmail(email);
		if(userDomain != null && userDomain.getId()>AppConstant.ZERO){
			//reset pass and send mail
			userDomain.setPassword(passEncoder.encode(AppConstant.PASSWORD_DEFAULT));
			repo.save(userDomain);
            //send mail
            StringBuilder content = new StringBuilder("Mật khẩu mới của bạn sau khi khôi phục là: ");

            sendMail(email,content.toString());
		}
	}

	@Override
	public AdminUsersDomain saveOrUpdate(AdminUsersDomain adminUsersDomain) {

		if(adminUsersDomain.getId() == null){
			Long isExitUsername = repo.isExitUsername(adminUsersDomain.getUserName());
			if(isExitUsername > AppConstant.ZERO)
				return null;
		}
		String passTransRandom = "";
		String passLoginRandom = PasswordUtils.generateRandomPassword(8);

		AdminUsersDomain userLogin = repo.findByUserNameAndDeleted(IContext.getUserNameLogin(),AppConstant.DELETED.NO);

		adminUsersDomain.setIsActive(AppConstant.ACTIVE.ACTIVE);
		adminUsersDomain.setFirstPassChange(AppConstant.PASS_CHANGE.NO);
		adminUsersDomain.setUserId(userLogin.getId());
		adminUsersDomain.setDateCreated(new Date());
		adminUsersDomain.setDeleted(AppConstant.DELETED.NO);
		adminUsersDomain.setStatus(AppConstant.STATUS.ACTIVE);
		adminUsersDomain.setNumberLogin(0L);
		adminUsersDomain.setUserNameCreate(userLogin.getUserName());
		adminUsersDomain.setPassword(passEncoder.encode(passLoginRandom));
		adminUsersDomain.setIsRoot(0L);

		if(adminUsersDomain.getStaffDomainAdd().getIsTransaction()==AppConstant.CONFIRM.YES) {
			passTransRandom = PasswordUtils.generateRandomPassword(6);
			adminUsersDomain.setPassTrans(passTransRandom);

		}

		repo.save(adminUsersDomain);

		//save role
		for(AdminRolesDomain role : adminUsersDomain.getLstRoles()){
			AdminUserRoleDomain aURole = new AdminUserRoleDomain();
			aURole.setUserId(adminUsersDomain.getId());
			aURole.setRoleId(role.getId());
			aURole.setDateCreated(new Date());
			aURole.setCreateBy(userLogin.getId());
			auRoleRepo.save(aURole);
		}

		//send mail
        StringBuilder content = new StringBuilder("Chào mừng bạn đến với hệ thống quản lý quỹ tập trung.");
		content.append(" Tài khoản của bạn đã được khởi tạo vào hồi <b>");
		content.append(DateTimeUltils.convertDateToString(Calendar.getInstance().getTime(),AppConstant.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS)).append("</b>");
        content.append(". Chúng tôi gửi đến bạn thông tin đăng nhập của tài khoản của bạn như sau:<br>");
        content.append("&#09; 1. Tên đăng nhập:<b>").append(adminUsersDomain.getUserName()).append("</b><br>");
		content.append("&#09; 2. Mật khẩu:<b>").append(passLoginRandom).append("</b><br>");
		if(adminUsersDomain.getStaffDomainAdd().getIsTransaction()==AppConstant.CONFIRM.YES) {
			content.append("&#09; 3. Mật khẩu giao dịch:<b>").append(passTransRandom).append("</b><br>");
		}
		content.append("Vui lòng không tiết lộ mật khẩu cho bất cứ ai và đổi mật khẩu với lần đăng nhập đầu tiên.<br><br>");
		content.append("Mọi thắc mắc vui lòng liên hệ <br>");
		content.append("&#09; Điện thoại: <b><a href=\"tel:0347555080\">0347555080</a></b><br>");
		content.append("&#09; Hoặc email: <b><a href=\"mailto:bankfunds.support@gmail.com\">bankfunds.support@gmail.com</a></b><br>");
		content.append("<b>Chúc bạn một ngày tốt lành!<b>");
        sendMail(adminUsersDomain.getStaffDomainAdd().getEmail(),content.toString());
		return adminUsersDomain;
	}


	private void sendMail(String email,String content){
		//gui mail cho nhan vien
//        SimpleMailMessage msg = new SimpleMailMessage();
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage);

			//msg.setTo("tamdt.swf@gmail.com", "tamdt@antsoft.vn");
			msg.setTo(email);
			msg.setSubject("[Bank Funds] Thông tin tài khoản mới");
			msg.setText(content,true);
		};

        javaMailSender.send(messagePreparator);

	}
}