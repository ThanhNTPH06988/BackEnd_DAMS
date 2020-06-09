package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.dtos.CasstteSearchDTO;

import java.util.List;

public interface AdminCasstteCustomRepository {
    void deletedCasstte(Long atmId);

    List<AdminCasstteDomain> searchCasstte(CasstteSearchDTO searchDTO);

    Long countSearchCasstte(CasstteSearchDTO searchDTO);

    Long exitCasstteCode(String code);

    void deletedCasstteByBranchId(Long branchId);
}