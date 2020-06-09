package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.dtos.StaffSearchDTO;

import java.util.List;

public interface AdminStaffCustomRepository {
    List<AdminStaffDomain> searchStaff(StaffSearchDTO searchDTO);

    Long countSearchStaff(StaffSearchDTO searchDTO);

    void deleteStaff(Long staffId);

    void lockOrUnlock(Long staffId, Long type);

    List<AdminStaffDomain> getStaffByBranchId(Long branchId, Long staffIdLogin);

    Long isExitStaffInUsers(Long staffId);

    Long isExitCode(Long branchId, String code);

    List<AdminStaffDomain> getStaffByBranchForStorages(Long branchId, Long storagesId);
}