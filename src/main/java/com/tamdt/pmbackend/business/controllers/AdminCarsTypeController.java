package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCarsTypeDomain;
import com.tamdt.pmbackend.business.services.AdminCarsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminCarsType")
public class AdminCarsTypeController extends BasicController<AdminCarsTypeDomain> {

	@Autowired
	private AdminCarsTypeService service;

	@Override
	public BasicService getService() {
		return service;
	}

}