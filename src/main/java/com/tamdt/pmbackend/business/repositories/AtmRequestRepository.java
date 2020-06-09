package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.repositories.custom.AtmRequestCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRequestRepository extends BasicRepository<AtmRequestDomain>,AtmRequestCustomRepository{
}