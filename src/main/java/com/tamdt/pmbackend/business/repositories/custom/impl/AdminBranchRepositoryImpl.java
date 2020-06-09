package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.dtos.BranchSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminBranchCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminBranchRepositoryImpl implements AdminBranchCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AdminBranchDomain>
	searchBranch(BranchSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select branch");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());

		return query.getResultList();
	}

	@Override
	public Long countSearchBranch(BranchSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(branch)");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);
		return (Long)query.getSingleResult();
	}

	private void createQuery(Query query, BranchSearchDTO searchDTO) {
		query.setParameter("deleted", AppConstant.DELETED.NO);
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code",StringUtils.toLikeString(searchDTO.getCode()));
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			query.setParameter("name",StringUtils.toLikeString(searchDTO.getName()));
		if(searchDTO.getDateFrom() != null)
			query.setParameter("fromDate",searchDTO.getDateFrom());
		if(searchDTO.getDateTo() != null)
			query.setParameter("toDate",searchDTO.getDateTo());
		if(searchDTO.getStatus() != null)
			query.setParameter("status",searchDTO.getStatus());
		if (!StringUtils.isNullOrEmpty(searchDTO.getNameManager())) {
			query.setParameter("managerName", StringUtils.toLikeString(searchDTO.getNameManager()));
		}
	}

	private void
	createStrQuery(StringBuilder sql, BranchSearchDTO searchDTO) {
		sql.append(" from AdminBranchDomain branch");
		sql.append(" left join AdminStaffDomain staff on branch.staffId = staff.id");
		sql.append(" where branch.deleted =:deleted");
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" and lower(branch.code) like :code");
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			sql.append(" and lower(branch.name) like :name");
		if(searchDTO.getDateFrom() != null)
			sql.append(" and TRUNC(branch.dateCreated) >= TRUNC(:fromDate)");
		if(searchDTO.getDateTo() != null)
			sql.append(" and TRUNC(branch.dateCreated) <= TRUNC(:toDate)");
		if(searchDTO.getStatus() != null)
			sql.append(" and branch.status =:status");
		if (!StringUtils.isNullOrEmpty(searchDTO.getNameManager())) {
			sql.append(" and lower(staff.name) like :managerName");
		}

		sql.append(" order by branch.dateCreated DESC");
	}

	@Override
	@Transactional
	public void deleteBranch(Long branchId) {
		StringBuilder sql = new StringBuilder("update ADMIN_BRANCH set DELETED=:deleted, STAFF_ID = NULL where ID=:branchId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted",AppConstant.DELETED.YES);
		query.setParameter("branchId",branchId);
		query.executeUpdate();
	}

    @Override
	@Transactional
    public void lockOrUnlock(Long branchId, Long type) {
		StringBuilder sql = new StringBuilder("update ADMIN_BRANCH set STATUS=:status where ID=:branchId");
		Query query = manager.createNativeQuery(sql.toString());
		if(type == AppConstant.STATUS.ACTIVE)
			query.setParameter("status",AppConstant.STATUS.INACTIVE);
		else
			query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("branchId",branchId);
		query.executeUpdate();
    }

	@Override
	@Transactional
	public Long isExitCode(String branchCode) {

		StringBuilder sql = new StringBuilder("select count(1) from admin_branch adBranch");
		sql.append(" where upper(adBranch.code) =:code and adBranch.DELETED =:deleted");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("code",branchCode.toUpperCase().trim());
		query.setParameter("deleted",AppConstant.DELETED.NO);

		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	@Transactional
	public void updateStaffId(Long id, Long staffId) {
		StringBuilder sql = new StringBuilder("update ADMIN_BRANCH set STAFF_ID = :staffId where ID = :branchId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("staffId", staffId);
		query.setParameter("branchId", id);
		query.executeUpdate();
	}

}