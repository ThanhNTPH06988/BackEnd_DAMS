package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminRolesDomain;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRolesRepository extends BasicRepository<AdminRolesDomain> {
}