package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.HisAtmRequestDomain;
import com.tamdt.pmbackend.business.repositories.HisAtmRequestRepository;
import com.tamdt.pmbackend.business.services.HisAtmRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HisAtmRequestServiceImpl extends BasicServiceImpl<HisAtmRequestDomain> implements HisAtmRequestService {

    @Autowired
    private HisAtmRequestRepository repo;

    @Override
    public BasicRepository<HisAtmRequestDomain> getRepository() {
        return repo;
    }

    @Override
    public List<HisAtmRequestDomain> getHisRequestByRequestId(Long requestId) {
        return repo.findByAtmRequestId(requestId);
    }
}

