package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.dtos.AtmSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminAtmRepository;
import com.tamdt.pmbackend.common.helper.ListJson;

import java.util.List;


public interface AdminAtmService extends BasicService<AdminAtmDomain> {
    void deletedAtm(Long userId);

    ListJson<AdminAtmDomain> searchAtms(AtmSearchDTO searchDTO);

    void  lockOrUnlock(Long atmId, Long type);

    List<AdminAtmDomain> getAtmByBranchCode(String branchCode); //huongmk dung phan nay


}