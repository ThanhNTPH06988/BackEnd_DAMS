package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.dtos.BranchSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;

import java.util.List;


public interface AdminBranchService extends BasicService<AdminBranchDomain> {
    List<AdminBranchDomain> getAllBranch();

    ListJson<AdminBranchDomain> searchBranch(BranchSearchDTO searchDTO);

    void deleteBranch(Long branchId);

    void lockOrUnlock(Long branchId, Long type);

    void updateStaffId(String staffCode, Long branchId);
}