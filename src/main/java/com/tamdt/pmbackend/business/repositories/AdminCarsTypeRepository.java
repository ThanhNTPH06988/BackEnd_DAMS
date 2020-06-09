package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminCarsTypeDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminCarsTypeCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCarsTypeRepository extends BasicRepository<AdminCarsTypeDomain>,AdminCarsTypeCustomRepository{
}