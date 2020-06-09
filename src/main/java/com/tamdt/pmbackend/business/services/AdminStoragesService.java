package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.dtos.StoragesSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface AdminStoragesService extends BasicService<AdminStoragesDomain> {
    List<AdminStaffDomain> getStaff();

    ListJson<AdminStoragesDomain> searchData(StoragesSearchDTO searchDTO);

    List<AdminStoragesDomain> getStorages();

    List<AdminStoragesDomain> getStoragesStatusDeleted();

    void lockOrUnlock(Long storId, Long type);

    void deleteStor(Long storId);
}