package com.tamdt.pmbackend.config.oauth2;


import com.tamdt.pmbackend.business.repositories.AdminUserRoleRepository;
import com.tamdt.pmbackend.common.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UsersOauthService implements UserDetailsService {

    @Autowired
    private AdminUsersOauth2Repository repo;

    @Autowired
    private AdminUserRoleRepository uRoleRepo;

//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//
//    @PostConstruct
//    private void initUser() {
//        AdminUsersDomain user1 = new AdminUsersDomain();
//        user1.setUserName("admin");
//        user1.setPassword(encoder.encode(AppConstant.PASSWORD_DEFAULT));
//        user1.setIsActive(1L);
//        user1.setFirstPassChange("Y");
//        user1.setDateCreated(new Date());
//        user1.setDeleted("N");
//        user1.setStatus(1L);
//        user1.setNumberLogin(0L);
//
//        repo.save(user1);
//
//    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AdminUsersOauth2Domain domain = repo.findByUserNameAndDeletedAndStatus(s, AppConstant.DELETED.NO,AppConstant.STATUS.ACTIVE);
        if (domain == null) {
            throw new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng.");
        }

//        findByUserNameAndDeleted

        return UserPrincipalOauth2.create(domain);

    }

}
