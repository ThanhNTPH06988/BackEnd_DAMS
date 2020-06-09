package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCarsTicketDomain;
import com.tamdt.pmbackend.business.services.AdminCarsTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminCarsTicket")
public class AdminCarsTicketController extends BasicController<AdminCarsTicketDomain> {

	@Autowired
	private AdminCarsTicketService service;

	@Override
	public BasicService getService() {
		return service;
	}

}