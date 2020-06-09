package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminStaffCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminStaffRepository extends BasicRepository<AdminStaffDomain>, AdminStaffCustomRepository {

    List<AdminStaffDomain> findByBranchIdAndStatusAndDeleted(Long branchId, Long status, String deleted);

    AdminStaffDomain findByIdAndStatusAndDeleted(Long staffId,Long status,String deleted);
	List<AdminStaffDomain> findByPositionIdAndStatusAndDeleted(Long warehouse, Long active, String no);

    List<AdminStaffDomain> findByBranchIdAndDeleted(Long branchId, String deleted);

    AdminStaffDomain findByCodeAndDeleted(String staffCode, String no);

    List<AdminStaffDomain> findByBranchId(Long branchId);
}