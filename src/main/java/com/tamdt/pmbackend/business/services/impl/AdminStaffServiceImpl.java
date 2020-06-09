package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.StaffSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminStaffRepository;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.business.services.AdminStaffService;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminStaffServiceImpl extends BasicServiceImpl<AdminStaffDomain> implements AdminStaffService {

	@Autowired
	private AdminStaffRepository repo;

	@Autowired
	private AdminUsersRepository userRepo;

	@Override
	public BasicRepository<AdminStaffDomain> getRepository() {
		return repo;
	}

	@Override
	public List<AdminStaffDomain> getStaffByBranchId(Long branchId) {
		AdminUsersDomain userLogin = userRepo.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);

		List<AdminStaffDomain> lstRs = new ArrayList<>();

//		if(userLogin.getIsRoot() == 1L){
//			lstRs = repo.findByBranchIdAndStatusAndDeleted(branchId,AppConstant.STATUS.ACTIVE,AppConstant.DELETED.NO);
//		}else {
//			lstRs = repo.findByBranchIdAndStatusAndDeleted(userLogin.getStaffDomain().getBranchId(), AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);
//		}

		if(userLogin.getIsRoot() == 1L){
			lstRs = repo.getStaffByBranchId(branchId,null);
		}else {
			lstRs = repo.getStaffByBranchId(userLogin.getStaffDomain().getBranchId(),userLogin.getStaffDomain().getId());
		}

		return lstRs;
	}

	@Override
	public ListJson<AdminStaffDomain> searchStaff(StaffSearchDTO searchDTO) {
		Long countSearch = repo.countSearchStaff(searchDTO);
		List<AdminStaffDomain> lstRs = new ArrayList<>();

		if(countSearch > 0L){
			lstRs = repo.searchStaff(searchDTO);
		}

		return new ListJson<AdminStaffDomain>(lstRs,(long)countSearch);
	}


	@Override
	public AdminStaffDomain saveOrUpdate(AdminStaffDomain adminStaffDomain) {

		if(adminStaffDomain.getId() == null){
			Long isExitCode = repo.isExitCode(adminStaffDomain.getBranchId(),adminStaffDomain.getCode());
			if(isExitCode > AppConstant.ZERO)
				return null;
		}

		//staff old
		AdminStaffDomain staffOld = repo.getOne(adminStaffDomain.getId());

		AdminUsersDomain userLogin = userRepo.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);

		if(adminStaffDomain.getId() == null) {
			//them moi
			adminStaffDomain.setDateCreated(new Date());
			adminStaffDomain.setStatus(AppConstant.STATUS.ACTIVE);
			adminStaffDomain.setDeleted(AppConstant.DELETED.NO);
			adminStaffDomain.setUserId(userLogin.getId());
		}else{
			//sua
			adminStaffDomain.setDateCreated(staffOld.getDateCreated());
			adminStaffDomain.setStatus(staffOld.getStatus());
			adminStaffDomain.setDeleted(staffOld.getDeleted());
			adminStaffDomain.setUserId(staffOld.getUserId());
		}

		super.saveOrUpdate(adminStaffDomain);

		return adminStaffDomain;
	}

	@Override
	public void deleteStaff(Long userId) {
		repo.deleteStaff(userId);
	}

	@Override
	@Transactional
	public void lockOrUnlock(Long staffId, Long type) {
		repo.lockOrUnlock(staffId,type);
	}

	@Override
	public Long isExitStaffInUsers(Long staffId) {
		return repo.isExitStaffInUsers(staffId);
	}

	@Override
	public List<AdminStaffDomain> getStaffByBranchForStorages(Long branchId, Long storagesId) {
		List<AdminStaffDomain> lstStaff = new ArrayList<>();
		lstStaff = repo.getStaffByBranchForStorages(branchId, storagesId);
		return lstStaff;
	}

}