package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.domains.AdminStaffPositionDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminBranchCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminBranchRepository extends BasicRepository<AdminBranchDomain>,AdminBranchCustomRepository{

    List<AdminBranchDomain> findByStatusAndDeleted(Long status,String deleted);

    AdminBranchDomain findByCode(String branchCode);

    List<AdminBranchDomain> findByStatusAndDeletedOrderByNameDesc(Long status, String deleted);

    AdminBranchDomain findByIdAndDeleted(Long id, String deleted);
}