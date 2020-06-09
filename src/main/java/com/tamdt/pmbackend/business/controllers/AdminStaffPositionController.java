package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffPositionDomain;
import com.tamdt.pmbackend.business.services.AdminStaffPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adminStaffPosition")
public class AdminStaffPositionController extends BasicController<AdminStaffPositionDomain> {

	@Autowired
	private AdminStaffPositionService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/getPosition", method = RequestMethod.GET)
	public List<AdminStaffPositionDomain> getPosition() {
		try {
			return service.getPosition();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}