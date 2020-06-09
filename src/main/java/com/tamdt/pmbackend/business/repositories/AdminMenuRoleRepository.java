package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminMenuRoleDomain;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMenuRoleRepository extends BasicRepository<AdminMenuRoleDomain> {
    void deleteByMenuId(Long menuId);
}