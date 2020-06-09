package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.StoragesSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminStaffRepository;
import com.tamdt.pmbackend.business.repositories.AdminStoragesRepository;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.business.services.AdminStoragesService;
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
public class AdminStoragesServiceImpl extends BasicServiceImpl<AdminStoragesDomain> implements AdminStoragesService {

    @Autowired
    private AdminStoragesRepository repo;

    @Autowired
    private AdminStaffRepository repoStaff;

    @Autowired
    private AdminUsersRepository repoUser;

    @Override
    public BasicRepository<AdminStoragesDomain> getRepository() {
        return repo;
    }

    @Override
    public List<AdminStaffDomain> getStaff() {
        return repoStaff.findByPositionIdAndStatusAndDeleted(AppConstant.STAFF_POSITION.WAREHOUSE, AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);
    }

    @Override
    public ListJson<AdminStoragesDomain> searchData(StoragesSearchDTO searchDTO) {
        List<AdminStoragesDomain> lstStorages = new ArrayList<>();
        Long count = repo.countSearchData(searchDTO);
        if (count > 0) {
            lstStorages = repo.searchData(searchDTO);
        }
        return new ListJson<AdminStoragesDomain>(lstStorages, (long) count);
    }

    @Override
    public List<AdminStoragesDomain> getStorages() {
        return repo.findByDeleted(AppConstant.DELETED.NO);
    }

    @Override
    public List<AdminStoragesDomain> getStoragesStatusDeleted() {
        return repo.findByDeletedAndStatus(AppConstant.DELETED.NO, AppConstant.STATUS.ACTIVE);
    }

    @Override
    @Transactional
    public void lockOrUnlock(Long storId, Long type) {
        repo.lockOrUnlock(storId,type);
    }

    @Override
    public void deleteStor(Long storId) {
        repo.deleteStor(storId);
    }

    @Override
    public AdminStoragesDomain saveOrUpdate(AdminStoragesDomain adminStoragesDomain) {

        // check ma kho trung lap

        List<AdminStoragesDomain> lstAdminSto = repo.findByCode(adminStoragesDomain.getCode());
        int isExist = lstAdminSto.size();
        if (isExist > 0 && adminStoragesDomain.getId() == null) {
            return new AdminStoragesDomain();
        }

        AdminUsersDomain userLogin = repoUser.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);
        adminStoragesDomain.setStatus(AppConstant.STATUS.ACTIVE);
        adminStoragesDomain.setDateCreated(new Date());
        adminStoragesDomain.setUserId(userLogin.getId());
        adminStoragesDomain.setDeleted(AppConstant.DELETED.NO);

        repo.save(adminStoragesDomain);
        return adminStoragesDomain;
    }
}