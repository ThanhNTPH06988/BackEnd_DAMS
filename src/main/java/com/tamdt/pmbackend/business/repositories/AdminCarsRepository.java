package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminCarsCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCarsRepository extends BasicRepository<AdminCarsDomain>,AdminCarsCustomRepository{
}