package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffPositionDomain;
import com.tamdt.pmbackend.business.repositories.AdminStaffPositionRepository;

import java.util.List;


public interface AdminStaffPositionService extends BasicService<AdminStaffPositionDomain> {
    List<AdminStaffPositionDomain> getPosition();
}