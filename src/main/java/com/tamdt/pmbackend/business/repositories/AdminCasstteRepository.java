package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminCasstteCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCasstteRepository extends BasicRepository<AdminCasstteDomain>,AdminCasstteCustomRepository{
}