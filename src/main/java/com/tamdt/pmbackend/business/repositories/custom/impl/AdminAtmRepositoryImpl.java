package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.dtos.AtmSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminAtmCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminAtmRepositoryImpl implements AdminAtmCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public void deletedAtm(Long atmId) {
		StringBuilder sql = new StringBuilder("update ADMIN_ATM set DELETED=:deleted where ID=:atmId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.YES);
		query.setParameter("atmId",atmId);
		query.executeUpdate();
	}

	@Override
	public List<AdminAtmDomain> searchAtms(AtmSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select atms");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());
		return query.getResultList();
	}

	@Override
	public Long countSearchAtms(AtmSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(atms)");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		return (Long)query.getSingleResult();
	}

	private void createQuery(Query query,AtmSearchDTO searchDTO){
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code", "%" + searchDTO.getCode().trim().toUpperCase() + "%");
		if(searchDTO.getBranchId() != null){
			query.setParameter("branchId", searchDTO.getBranchId());
		}
		if(searchDTO.getDateFrom() != null){
			query.setParameter("dateFrom", searchDTO.getDateFrom());
		}
		if(searchDTO.getDateTo() != null){
			query.setParameter("dateTo", searchDTO.getDateTo());
		}
		if(!StringUtils.isNullOrEmpty(searchDTO.getAddr()))
			query.setParameter("addrSearch", StringUtils.toLikeString(searchDTO.getAddr()));
		query.setParameter("deleted", AppConstant.DELETED.NO);
	}

	private void createStrQuery(StringBuilder sql,AtmSearchDTO searchDTO){

		sql.append(" FROM AdminAtmDomain atms");
		sql.append(" WHERE 1 =1");
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode())){
			sql.append(" AND upper(atms.code) like :code");
		}
		if(searchDTO.getBranchId() != null){
			sql.append(" AND atms.branchId = :branchId");
		}
		if(searchDTO.getDateFrom() != null){
			sql.append(" AND TRUNC(atms.dateCreated) >= TRUNC(:dateFrom)");
		}
		if(searchDTO.getDateTo() != null){
			sql.append(" AND TRUNC(atms.dateCreated) <= TRUNC(:dateTo)");
		}
		if(!StringUtils.isNullOrEmpty(searchDTO.getAddr())){
			sql.append(" AND (");
			sql.append("lower(atms.addrDetail) like :addrSearch");
			sql.append(" OR lower(atms.addr.fullName) like :addrSearch");
			sql.append(")");
		}

		sql.append(" AND atms.deleted =:deleted");
		sql.append(" ORDER BY atms.dateCreated DESC");
	}

	@Override
	public Long exitAtmCode(String code) {

		StringBuilder sql = new StringBuilder("select count(1) from ADMIN_ATM atm");
		sql.append(" where UPPER(atm.CODE) =:code and atm.DELETED =:deleted");

		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("code",code.toUpperCase());
		query.setParameter("deleted",AppConstant.DELETED.NO);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	@Transactional
	public void lockOrUnlock(Long atmId, Long type) {
		StringBuilder sql = new StringBuilder("update ADMIN_ATM set STATUS=:status where ID=:atmId");
		Query query = manager.createNativeQuery(sql.toString());
		if(type == AppConstant.STATUS.ACTIVE)
			query.setParameter("status",AppConstant.STATUS.INACTIVE);
		else
			query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("atmId",atmId);
		query.executeUpdate();
	}

@Override
	@Transactional
	public void deletedAtmByBranchId(Long branchId) {
		StringBuilder sql = new StringBuilder("update ADMIN_ATM set DELETED = :deleted where BRANCH_ID = :branchId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.YES);
		query.setParameter("branchId",branchId);
		query.executeUpdate();
	}
}