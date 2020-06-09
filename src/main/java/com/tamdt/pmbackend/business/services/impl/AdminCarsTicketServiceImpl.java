package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminCarsTicketDomain;
import com.tamdt.pmbackend.business.repositories.AdminCarsTicketRepository;
import com.tamdt.pmbackend.business.services.AdminCarsTicketService;
import org.springframework.stereotype.Service;


@Service
public class AdminCarsTicketServiceImpl extends BasicServiceImpl<AdminCarsTicketDomain> implements AdminCarsTicketService {

	@Autowired
	private AdminCarsTicketRepository repo;

	@Override
	public BasicRepository<AdminCarsTicketDomain> getRepository() {
		return repo;
	}

}