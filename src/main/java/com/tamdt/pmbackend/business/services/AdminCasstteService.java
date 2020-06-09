package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.dtos.CasstteSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminCasstteRepository;
import com.tamdt.pmbackend.common.helper.ListJson;


public interface AdminCasstteService extends BasicService<AdminCasstteDomain> {
    void deletedCasstte(Long userId);

    ListJson<AdminCasstteDomain> searchCasstte(CasstteSearchDTO searchDTO);
}