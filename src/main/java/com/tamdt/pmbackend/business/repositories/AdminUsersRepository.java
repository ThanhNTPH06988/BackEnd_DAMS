package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminUsersCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUsersRepository extends BasicRepository<AdminUsersDomain>, AdminUsersCustomRepository {
    AdminUsersDomain findByUserNameAndDeleted(String username,String deleted);
}