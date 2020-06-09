package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.dtos.BranchSearchDTO;

import java.util.List;

public interface AdminBranchCustomRepository {
    List<AdminBranchDomain> searchBranch(BranchSearchDTO searchDTO);
    Long countSearchBranch(BranchSearchDTO searchDTO);
    void deleteBranch(Long branchId);
    void lockOrUnlock(Long branchId, Long type);
    Long isExitCode(String branchCode);

    void updateStaffId(Long id, Long staffId);
}