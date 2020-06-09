package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.dtos.StoragesSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminStoragesCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class AdminStoragesRepositoryImpl implements AdminStoragesCustomRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<AdminStoragesDomain> searchData(StoragesSearchDTO searchDTO) {

        int firstRecord = searchDTO.getPage() * searchDTO.getSize();
        StringBuilder sql = new StringBuilder("select sto");
        createStrQuery(sql, searchDTO);
        Query query = manager.createQuery(sql.toString());
        createQuery(query, searchDTO);
        query.setFirstResult(firstRecord);
        query.setMaxResults(searchDTO.getSize());

        return query.getResultList();
    }

    @Override
    public Long countSearchData(StoragesSearchDTO searchDTO) {
        StringBuilder sql = new StringBuilder("select count(sto)");
        createStrQuery(sql, searchDTO);
        Query query = manager.createQuery(sql.toString());
        createQuery(query, searchDTO);
        return (Long) query.getSingleResult();
    }

    @Override
    public void lockOrUnlock(Long storId, Long type) {
        StringBuilder sql = new StringBuilder("update ADMIN_STORAGES set STATUS=:status where ID=:storId");
        Query query = manager.createNativeQuery(sql.toString());
        if(type == AppConstant.STATUS.ACTIVE)
            query.setParameter("status",AppConstant.STATUS.INACTIVE);
        else
            query.setParameter("status",AppConstant.STATUS.ACTIVE);
        query.setParameter("storId",storId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteStor(Long storId) {
        StringBuilder sql = new StringBuilder("update ADMIN_STORAGES set DELETED=:deleted where ID=:storId");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("deleted",AppConstant.DELETED.YES);
        query.setParameter("storId",storId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deletedStoragesByBranchId(Long branchId) {
        StringBuilder sql = new StringBuilder("update ADMIN_STORAGES set DELETED = :deleted where BRANCH_ID = :branchId");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("deleted",AppConstant.DELETED.YES);
        query.setParameter("branchId",branchId);
        query.executeUpdate();
    }

    private void createQuery(Query query, StoragesSearchDTO searchDTO) {
        if (!StringUtils.isNullOrEmpty(searchDTO.getCode())) {
            query.setParameter("codeSto", StringUtils.toLikeString(searchDTO.getCode()));
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getName())) {
            query.setParameter("nameSto", StringUtils.toLikeString(searchDTO.getName()));
        }
        if (searchDTO.getBranchId() != null) {
            query.setParameter("branchId", searchDTO.getBranchId());
        }
        if (searchDTO.getStatus() != null) {
            query.setParameter("statusId", searchDTO.getStatus());
        }
        if (searchDTO.getDateFrom() != null) {
            query.setParameter("dateFrom", searchDTO.getDateFrom());
        }
        if (searchDTO.getDateTo() != null) {
            query.setParameter("dateTo", searchDTO.getDateTo());
        }
        query.setParameter("deleted", AppConstant.DELETED.NO);
    }

    private void createStrQuery(StringBuilder sql, StoragesSearchDTO searchDTO) {
        sql.append(" from AdminStoragesDomain sto");
        sql.append(" where 1=1");
        if (!StringUtils.isNullOrEmpty(searchDTO.getCode())) {
            sql.append(" and lower(sto.code) like :codeSto");
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getName())) {
            sql.append(" and lower(sto.name) like :nameSto");
        }
        if (searchDTO.getBranchId() != null) {
            sql.append(" and sto.branchId = :branchId");
        }
        if (searchDTO.getStatus() != null) {
            sql.append(" and sto.status = :statusId");
        }
        if (searchDTO.getDateFrom() != null) {
            sql.append(" and trunc(sto.dateCreated) >= trunc(:dateFrom)");
        }
        if (searchDTO.getDateTo() != null) {
            sql.append(" and trunc(sto.dateCreated) <= trunc(:dateTo)");
        }
        sql.append(" and sto.deleted = :deleted");
        sql.append(" order by sto.dateCreated desc");
    }
}