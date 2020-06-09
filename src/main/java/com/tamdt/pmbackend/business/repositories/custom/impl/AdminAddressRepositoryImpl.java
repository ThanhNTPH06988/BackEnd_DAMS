package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.repositories.custom.AdminAddressCustomRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AdminAddressRepositoryImpl implements AdminAddressCustomRepository {

	@PersistenceContext
	private EntityManager manager;

}