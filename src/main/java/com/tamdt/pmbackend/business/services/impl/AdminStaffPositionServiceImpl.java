package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminStaffPositionDomain;
import com.tamdt.pmbackend.business.repositories.AdminStaffPositionRepository;
import com.tamdt.pmbackend.business.services.AdminStaffPositionService;
import com.tamdt.pmbackend.common.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminStaffPositionServiceImpl extends BasicServiceImpl<AdminStaffPositionDomain> implements AdminStaffPositionService {

	@Autowired
	private AdminStaffPositionRepository repo;

	@Override
	public BasicRepository<AdminStaffPositionDomain> getRepository() {
		return repo;
	}

	@Override
	public List<AdminStaffPositionDomain> getPosition() {
		return repo.findByDeletedAndStatusOrderByName(AppConstant.DELETED.NO, AppConstant.STATUS.ACTIVE);
	}
}