package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.HisAtmRequestDomain;

import java.util.List;


public interface HisAtmRequestService extends BasicService<HisAtmRequestDomain> {
    List<HisAtmRequestDomain> getHisRequestByRequestId(Long requestId);
}