package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminStaffPositionDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminStaffPositionCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminStaffPositionRepository extends BasicRepository<AdminStaffPositionDomain>,AdminStaffPositionCustomRepository{
    List<AdminStaffPositionDomain> findByDeletedAndStatusOrderByName(String no, Long active);
}