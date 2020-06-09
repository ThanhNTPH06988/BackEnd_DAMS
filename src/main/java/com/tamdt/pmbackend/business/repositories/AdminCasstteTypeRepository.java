package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminCasstteTypeDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminCasstteTypeRepository extends BasicRepository<AdminCasstteTypeDomain>{
    List<AdminCasstteTypeDomain> findByDeletedAndStatus(String deleted, Long status);
    List<AdminCasstteTypeDomain> findByStatusAndDeleted(Long status, String deleted);
}