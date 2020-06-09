package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCasstteTypeDomain;
import com.tamdt.pmbackend.business.services.AdminCasstteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adminCasstteType")
public class AdminCasstteTypeController extends BasicController<AdminCasstteTypeDomain> {

	@Autowired
	private AdminCasstteTypeService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public List<AdminCasstteTypeDomain> getAll(){
		try{
			return service.getAll();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

}