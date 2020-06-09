package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.repositories.custom.AdminStaffPositionCustomRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AdminStaffPositionRepositoryImpl implements AdminStaffPositionCustomRepository {
	@PersistenceContext
	private EntityManager manager;

}