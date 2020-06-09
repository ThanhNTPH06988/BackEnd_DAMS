package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.repositories.custom.AdminCarsTypeCustomRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AdminCarsTypeRepositoryImpl implements AdminCarsTypeCustomRepository {
	@PersistenceContext
	private EntityManager manager;

}