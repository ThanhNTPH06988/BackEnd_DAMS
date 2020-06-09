package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.HisLoginDomain;
import org.springframework.stereotype.Repository;

@Repository
public interface HisLoginRepository extends BasicRepository<HisLoginDomain> {
}