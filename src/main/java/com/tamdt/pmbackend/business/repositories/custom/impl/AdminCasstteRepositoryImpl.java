package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.dtos.CasstteSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminCasstteCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminCasstteRepositoryImpl implements AdminCasstteCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public void deletedCasstte(Long cassId) {
		StringBuilder sql = new StringBuilder("update ADMIN_CASSTTE set DELETED=:deleted where ID=:cassId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.YES);
		query.setParameter("cassId",cassId);
		query.executeUpdate();
	}

	@Override
	public List<AdminCasstteDomain> searchCasstte(CasstteSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select cass");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());
		return query.getResultList();
	}

	@Override
	public Long countSearchCasstte(CasstteSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(cass)");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		return (Long)query.getSingleResult();
	}

	private void createQuery(Query query,CasstteSearchDTO searchDTO){
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code", "%" + searchDTO.getCode().trim().toUpperCase() + "%");
		if(searchDTO.getStatus() != null)
			query.setParameter("status", searchDTO.getStatus());
		if(searchDTO.getTypeId() != null)
			query.setParameter("casstteTypeId", searchDTO.getTypeId());
		query.setParameter("deleted", AppConstant.DELETED.NO);
	}

	private void createStrQuery(StringBuilder sql,CasstteSearchDTO searchDTO){

		sql.append(" FROM AdminCasstteDomain cass");
		sql.append(" WHERE 1 =1");

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" AND upper(cass.code) like :code");
		if(searchDTO.getStatus() != null)
			sql.append(" AND cass.status =:status");
		if(searchDTO.getTypeId() != null)
			sql.append(" AND cass.casstteTypeId =:casstteTypeId");

		sql.append(" AND cass.deleted =:deleted");
		sql.append(" ORDER BY cass.id ASC");
	}

	@Override
	public Long exitCasstteCode(String code) {

		StringBuilder sql = new StringBuilder("select count(1) from ADMIN_ATM cass");
		sql.append(" where UPPER(cass.CODE) =:code and cass.DELETED =:deleted");

		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("code",code.toUpperCase());
		query.setParameter("deleted",AppConstant.DELETED.NO);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	@Transactional
	public void deletedCasstteByBranchId(Long branchId) {
		StringBuilder sql = new StringBuilder("update ADMIN_CASSTTE set DELETED = :deleted where BRANCH_ID = :branchId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.YES);
		query.setParameter("branchId", branchId);
		query.executeUpdate();
	}

}