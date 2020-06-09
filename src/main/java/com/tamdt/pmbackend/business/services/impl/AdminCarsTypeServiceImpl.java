package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminCarsTypeDomain;
import com.tamdt.pmbackend.business.repositories.AdminCarsTypeRepository;
import com.tamdt.pmbackend.business.services.AdminCarsTypeService;
import org.springframework.stereotype.Service;


@Service
public class AdminCarsTypeServiceImpl extends BasicServiceImpl<AdminCarsTypeDomain> implements AdminCarsTypeService {

	@Autowired
	private AdminCarsTypeRepository repo;

	@Override
	public BasicRepository<AdminCarsTypeDomain> getRepository() {
		return repo;
	}

}