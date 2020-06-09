package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.dtos.AtmSearchDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminAtmCustomRepository {
    void deletedAtm(Long atmId);

    List<AdminAtmDomain> searchAtms(AtmSearchDTO searchDTO);

    Long countSearchAtms(AtmSearchDTO searchDTO);

    Long exitAtmCode(String code);

    void lockOrUnlock(Long atmId, Long type);
void deletedAtmByBranchId(Long branchId);
}