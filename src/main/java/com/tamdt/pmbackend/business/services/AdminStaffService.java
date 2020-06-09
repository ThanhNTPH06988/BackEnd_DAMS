package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.dtos.StaffSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;

import java.util.List;


public interface AdminStaffService extends BasicService<AdminStaffDomain> {
    List<AdminStaffDomain> getStaffByBranchId(Long branchId);

    ListJson<AdminStaffDomain> searchStaff(StaffSearchDTO searchDTO);

    void deleteStaff(Long userId);

    void lockOrUnlock(Long staffId, Long type);

    Long isExitStaffInUsers(Long staffId);

    List<AdminStaffDomain> getStaffByBranchForStorages(Long branchId, Long storagesId);
}