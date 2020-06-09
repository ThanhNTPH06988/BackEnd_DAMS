package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminUserRoleDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRoleRepository extends BasicRepository<AdminUserRoleDomain> {

    void deleteByUserId(Long userId);
    List<AdminUserRoleDomain> findByUserId(Long userId);

}