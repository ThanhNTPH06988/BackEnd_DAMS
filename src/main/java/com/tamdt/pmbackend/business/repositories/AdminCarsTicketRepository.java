package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminCarsTicketDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminCarsTicketCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCarsTicketRepository extends BasicRepository<AdminCarsTicketDomain>,AdminCarsTicketCustomRepository{
}