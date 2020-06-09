package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.dtos.RequestSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AtmRequestCustomRepository;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AtmRequestRepositoryImpl implements AtmRequestCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AtmRequestDomain> searchData(RequestSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select atm");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());
		return query.getResultList();
	}

	@Override
	public Long countSearchData(RequestSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(atm)");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		return (Long) query.getSingleResult();
	}

	private void createStrQuery(StringBuilder sql, RequestSearchDTO searchDTO) {
		sql.append(" from AtmRequestDomain atm");
		sql.append(" where 1=1");
		if (!StringUtils.isNullOrEmpty(searchDTO.getCode())) {
			sql.append(" and lower(atm.code) like :codeRequest");
		}
		if (!StringUtils.isNullOrEmpty(searchDTO.getBranchId())) {
			sql.append(" and atm.branchCode = :branchCode");
		}
		if (searchDTO.getDateFrom() != null) {
			sql.append(" and trunc(atm.dateRequestFrom) >= trunc(:dateRequestFrom)");
		}
		if (searchDTO.getDateTo() != null) {
			sql.append(" and trunc(atm.dateRequestFrom) <= trunc(:daterequestTo)");
		}
		if (searchDTO.getStatusId() != null) {
			sql.append(" and atm.status = :statusRequest");
		}
		sql.append(" order by atm.dateRequestFrom desc");
	}

	private void createQuery(Query query, RequestSearchDTO searchDTO) {
		if (!StringUtils.isNullOrEmpty(searchDTO.getCode())) {
			query.setParameter("codeRequest", StringUtils.toLikeString(searchDTO.getCode()));
		}
		if (!StringUtils.isNullOrEmpty(searchDTO.getBranchId())) {
			query.setParameter("branchCode", searchDTO.getBranchId());
		}
		if (searchDTO.getDateFrom() != null) {
			query.setParameter("dateRequestFrom", searchDTO.getDateFrom());
		}
		if (searchDTO.getDateTo() != null) {
			query.setParameter("daterequestTo", searchDTO.getDateTo());
		}
		if (searchDTO.getStatusId() != null) {
			query.setParameter("statusRequest", searchDTO.getStatusId());
		}
	}
}