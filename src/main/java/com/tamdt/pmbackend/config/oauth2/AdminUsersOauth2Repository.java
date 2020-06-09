package com.tamdt.pmbackend.config.oauth2;

import com.tamdt.pmbackend.base.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUsersOauth2Repository extends BasicRepository<AdminUsersOauth2Domain> {
    AdminUsersOauth2Domain findByUserNameAndDeletedAndStatus(String username, String deleted,Long status);
}