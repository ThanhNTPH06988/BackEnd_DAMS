package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.dtos.StoragesSearchDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface AdminStoragesCustomRepository {
    List<AdminStoragesDomain> searchData(StoragesSearchDTO searchDTO);

    Long countSearchData(StoragesSearchDTO searchDTO);

    void lockOrUnlock(Long storId, Long type);

    void deleteStor(Long storId);

    void deletedStoragesByBranchId(Long branchId);
}