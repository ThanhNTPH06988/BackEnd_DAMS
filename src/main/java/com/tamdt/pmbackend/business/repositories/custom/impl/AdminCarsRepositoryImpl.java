package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.dtos.CarsSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminCarsCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminCarsRepositoryImpl implements AdminCarsCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AdminCarsDomain> searchCars(CarsSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("SELECT cars");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());
		return query.getResultList();
	}

	@Override
	public Long isExitCode(String code) {
		StringBuilder sql = new StringBuilder("select count(1) from ADMIN_CARS cars");

		sql.append(" where upper(cars.CODE) =:code");

		Query query = manager.createNativeQuery(sql.toString());

		query.setParameter("code",code.toUpperCase().trim());

		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	public Long countSearchCars(CarsSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(*)");
		createStrQuery(sql, searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query, searchDTO);
		return (Long)query.getSingleResult();
	}

	private void createQuery(Query query, CarsSearchDTO searchDTO) {
		query.setParameter("deleted", AppConstant.DELETED.NO);

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code", "%" + searchDTO.getCode().trim().toUpperCase() + "%");
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			query.setParameter("name",  "%" + searchDTO.getName().trim().toUpperCase() + "%");
		if(!StringUtils.isNullOrEmpty(searchDTO.getLicenseNumber()))
			query.setParameter("licenseNumber",  "%" + searchDTO.getLicenseNumber().trim().toUpperCase() + "%");
		if(searchDTO.getCarTypeId() != null)
			query.setParameter("carTypeId", searchDTO.getCarTypeId());
		if(searchDTO.getTicketId() != null)
			query.setParameter("ticketId", searchDTO.getTicketId());
		if(searchDTO.getBrandId() != null)
			query.setParameter("brandId", searchDTO.getBrandId());
		if(searchDTO.getStatus() != null)
			query.setParameter("status", searchDTO.getStatus());
	}

	private void createStrQuery(StringBuilder sql, CarsSearchDTO searchDTO) {
		sql.append(" FROM AdminCarsDomain cars");
		sql.append(" WHERE cars.deleted =:deleted");

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" AND upper(cars.code) like :code");
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			sql.append(" AND upper(cars.name) like :name");
		if(!StringUtils.isNullOrEmpty(searchDTO.getLicenseNumber()))
			sql.append(" AND upper(cars.licenseNumber) like :licenseNumber");
		if(searchDTO.getCarTypeId() != null)
			sql.append(" AND cars.carTypeId =:carTypeId");
		if(searchDTO.getTicketId() != null)
			sql.append(" AND cars.ticketId =:ticketId");
		if(searchDTO.getBrandId() != null)
			sql.append(" AND cars.brandId =:brandId");
		if(searchDTO.getStatus() != null)
			sql.append(" AND cars.status =:status");

		sql.append(" ORDER BY cars.id ASC");
	}

	@Override
	@Transactional
	public void deletedCars(Long id) {
		StringBuilder sql = new StringBuilder("update ADMIN_CARS set DELETED =:deleted");
		sql.append(" where id=:id");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted",AppConstant.DELETED.YES);
		query.setParameter("id",id);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deletedCarsByBranchId(Long branchId) {
		StringBuilder sql = new StringBuilder("update ADMIN_CARS set DELETED = :deleted");
		sql.append(" where BRANCH_ID = :branchId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.YES);
		query.setParameter("branchId", branchId);
		query.executeUpdate();
	}

}