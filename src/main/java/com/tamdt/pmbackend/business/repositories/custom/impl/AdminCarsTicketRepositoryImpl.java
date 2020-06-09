package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.repositories.custom.AdminCarsTicketCustomRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AdminCarsTicketRepositoryImpl implements AdminCarsTicketCustomRepository {
	@PersistenceContext
	private EntityManager manager;

}