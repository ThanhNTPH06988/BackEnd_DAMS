package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.HisAtmRequestDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HisAtmRequestRepository extends BasicRepository<HisAtmRequestDomain> {

    List<HisAtmRequestDomain> findByAtmRequestId(Long requestId);

}