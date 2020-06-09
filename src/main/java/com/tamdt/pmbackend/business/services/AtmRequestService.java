package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.dtos.RequestSearchDTO;
import com.tamdt.pmbackend.business.repositories.AtmRequestRepository;
import com.tamdt.pmbackend.common.helper.ListJson;


public interface AtmRequestService extends BasicService<AtmRequestDomain> {
    ListJson<AtmRequestDomain> searchData(RequestSearchDTO dtoSearch);
}