package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.common.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminCasstteTypeDomain;
import com.tamdt.pmbackend.business.repositories.AdminCasstteTypeRepository;
import com.tamdt.pmbackend.business.services.AdminCasstteTypeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminCasstteTypeServiceImpl extends BasicServiceImpl<AdminCasstteTypeDomain> implements AdminCasstteTypeService {

	@Autowired
	private AdminCasstteTypeRepository repo;

	@Override
	public BasicRepository<AdminCasstteTypeDomain> getRepository() {
		return repo;
	}

	@Override
	public List<AdminCasstteTypeDomain> getAll() {
		return repo.findByDeletedAndStatus(AppConstant.DELETED.NO, AppConstant.STATUS.ACTIVE);
	}

}