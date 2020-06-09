package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.BranchSearchDTO;
import com.tamdt.pmbackend.business.repositories.*;
import com.tamdt.pmbackend.business.services.AdminBranchService;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminBranchServiceImpl extends BasicServiceImpl<AdminBranchDomain> implements AdminBranchService {

    @Autowired
    private AdminBranchRepository repo;

    @Autowired
    private AdminUsersRepository repoUser;

    @Autowired
    private AdminStaffRepository repoStaff;

    @Autowired
    private AdminStoragesRepository repoStorages;

    @Autowired
    private AdminAtmRepository repoAtm;

    @Autowired
    private AdminCarsRepository repoCar;

    @Autowired
    private AdminCasstteRepository repoCassette;

    @Override
    public BasicRepository<AdminBranchDomain> getRepository() {
        return repo;
    }

    @Override
    public List<AdminBranchDomain> getAllBranch() {
        return repo.findByStatusAndDeletedOrderByNameDesc(AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);
    }

    @Override
    public ListJson<AdminBranchDomain> searchBranch(BranchSearchDTO searchDTO) {
        Long countSearch = repo.countSearchBranch(searchDTO);
        List<AdminBranchDomain> lstRs = new ArrayList<>();

        if (countSearch > 0L) {
            lstRs = repo.searchBranch(searchDTO);

            for (AdminBranchDomain branch : lstRs) {
                AdminStaffDomain staff = repoStaff.findByIdAndStatusAndDeleted(branch.getStaffId(), AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);
                if (staff != null) {
                    branch.setManagerName(staff.getName());
                    branch.setStaffCode(staff.getCode());
                } else {
                    branch.setManagerName("");
                }
            }
        }

        return new ListJson<AdminBranchDomain>(lstRs, (long) countSearch);
    }

    @Override
    public AdminBranchDomain saveOrUpdate(AdminBranchDomain adminBranchDomain) {
        AdminUsersDomain userLogin = repoUser.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);
        AdminBranchDomain branchOld = null;
        boolean isEditCheck = false;
        if(adminBranchDomain.getId() != null){
            branchOld = repo.getOne(adminBranchDomain.getId());
            isEditCheck = branchOld != null && !branchOld.getCode().toLowerCase().equals(adminBranchDomain.getCode().toLowerCase());
        }

        if (adminBranchDomain.getId() == null || isEditCheck) {
            //them moi
            Long isExit = repo.isExitCode(adminBranchDomain.getCode());
            if (isExit > AppConstant.ZERO)
                return null;
        }

        if (adminBranchDomain.getId() == null) {
            // Them moi set cac gia tri mac dinh co cac bien
            adminBranchDomain.setStatus(AppConstant.STATUS.ACTIVE);
            adminBranchDomain.setDateCreated(new Date());
            adminBranchDomain.setUserId(userLogin.getId());
            adminBranchDomain.setDeleted(AppConstant.DELETED.NO);
        } else {
            //sua
            adminBranchDomain.setStatus(branchOld.getStatus());
            adminBranchDomain.setDateCreated(branchOld.getDateCreated());
            adminBranchDomain.setUserId(branchOld.getUserId());
            adminBranchDomain.setDeleted(branchOld.getDeleted());
        }

        return repo.save(adminBranchDomain);
    }

    @Override
    public void deleteBranch(Long branchId) {
        // xoa chi nhanh
        repo.deleteBranch(branchId);

        //cap nhat trang thai deleted thanh Y cho cac kho tien thuoc chi nhanh do
        repoStorages.deletedStoragesByBranchId(branchId);

        // cap nhat trang thai deleted thanh Y cho cac ATM thuoc chi nhanh do
        repoAtm.deletedAtmByBranchId(branchId);

        //cap nhat trang thai deleted thanh Y cho cac xe van chuyen thuoc chi nhanh do
        repoCar.deletedCarsByBranchId(branchId);

        //cap nhat trang thai deleted thanh Y cho cac cassette thuoc chi nhanh do
        repoCassette.deletedCasstteByBranchId(branchId);

        //tim kiem cac nhan vien thuoc chi nhanh duoc xoa nay
        List<AdminStaffDomain> lstStaff = repoStaff.findByBranchId(branchId);
        // cap nhat trang thai deleted cho cac nhan vien va tai khoan nhan vien thuoc chi nhanh do thanh Y
        for (AdminStaffDomain staff : lstStaff) {
            repoStaff.deleteStaff(staff.getId());
            repoUser.deletedUsersByStaffId(staff.getId());
        }

    }

    @Override
    @Transactional
    public void lockOrUnlock(Long branchId, Long type) {
        repo.lockOrUnlock(branchId, type);
    }

    @Override
    public void updateStaffId(String staffCode, Long branchId) {
        // lay ra nhan vien
        AdminStaffDomain staff = repoStaff.findByCodeAndDeleted(staffCode, AppConstant.DELETED.NO);
        if (staff != null) {
            repo.updateStaffId(branchId, staff.getId());
        } else {

        }
    }

}