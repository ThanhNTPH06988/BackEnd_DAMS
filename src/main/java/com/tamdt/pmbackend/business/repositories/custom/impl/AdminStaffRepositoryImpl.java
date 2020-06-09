package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.dtos.StaffSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminStaffCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminStaffRepositoryImpl implements AdminStaffCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AdminStaffDomain> searchStaff(StaffSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select staff ");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());

		return query.getResultList();
	}

	@Override
	public Long countSearchStaff(StaffSearchDTO searchDTO) {

		StringBuilder sql = new StringBuilder("select count(staff) ");
		createStrQuery(sql,searchDTO);
		Query query = manager.createQuery(sql.toString());
		createQuery(query,searchDTO);

		return (Long)query.getSingleResult();
	}

	private void createStrQuery(StringBuilder sql, StaffSearchDTO searchDTO) {
		sql.append(" from AdminStaffDomain staff");
		sql.append(" where staff.deleted =:deleted");

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" and lower(staff.code) like :code");
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			sql.append(" and lower(staff.name) like :name");
		if(!StringUtils.isNullOrEmpty(searchDTO.getIdcard()))
			sql.append(" and lower(staff.idcard) like :idcard");
		if(searchDTO.getBranchId() != null)
			sql.append(" and staff.branchId =:branchId");
		if(searchDTO.getPositionId() != null)
			sql.append(" and staff.positionId =:positionId");
		if(searchDTO.getStatus() != null)
			sql.append(" and staff.status =:status");

		sql.append(" order by staff.id DESC");
	}

	private void createQuery(Query query, StaffSearchDTO searchDTO) {
		query.setParameter("deleted", AppConstant.DELETED.NO);
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code",StringUtils.toLikeString(searchDTO.getCode()));
		if(!StringUtils.isNullOrEmpty(searchDTO.getName()))
			query.setParameter("name",StringUtils.toLikeString(searchDTO.getName()));
		if(!StringUtils.isNullOrEmpty(searchDTO.getIdcard()))
			query.setParameter("idcard",StringUtils.toLikeString(searchDTO.getIdcard()));
		if(searchDTO.getBranchId() != null)
			query.setParameter("branchId",searchDTO.getBranchId());
		if(searchDTO.getPositionId() != null)
			query.setParameter("positionId",searchDTO.getPositionId());
		if(searchDTO.getStatus() != null)
			query.setParameter("status",searchDTO.getStatus());

	}

	@Override
	@Transactional
	public void deleteStaff(Long staffId) {
		StringBuilder sql = new StringBuilder("update ADMIN_STAFF set DELETED=:deleted where ID=:staffId");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted",AppConstant.DELETED.YES);
		query.setParameter("staffId",staffId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void lockOrUnlock(Long staffId, Long type) {
		StringBuilder sql = new StringBuilder("update ADMIN_STAFF set STATUS=:status where ID=:staffId ");
		Query query = manager.createNativeQuery(sql.toString());
		if(type == AppConstant.STATUS.ACTIVE)
			query.setParameter("status",AppConstant.STATUS.INACTIVE);
		else
			query.setParameter("status", AppConstant.STATUS.ACTIVE);
		query.setParameter("staffId", staffId);
		query.executeUpdate();
	}

	@Override
	public List<AdminStaffDomain> getStaffByBranchId(Long branchId, Long staffIdLogin) {
		StringBuilder sql = new StringBuilder("select staff from AdminStaffDomain staff");
		sql.append(" where staff.status=:status and staff.deleted=:deleted");
		sql.append(" and staff.branchId=:branchId ");
		sql.append(" and staff.id not in (");
		sql.append(" select adStaff.id from AdminUsersDomain adUsers");
		sql.append(" inner join AdminStaffDomain adStaff on adUsers.staffId=adStaff.id");
		sql.append(" where adStaff.branchId =:branchId");
		sql.append(")");
		if(staffIdLogin != null)
			sql.append(" and staff.id !=:staffId");

		Query query = manager.createQuery(sql.toString());
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);
		query.setParameter("branchId",branchId);
		if(staffIdLogin != null)
			query.setParameter("staffId",staffIdLogin);

		return query.getResultList();
	}

	@Override
	public Long isExitStaffInUsers(Long staffId) {
		StringBuilder sql = new StringBuilder("select count(1) from ADMIN_USERS adUsers");
		sql.append(" where adUsers.STAFF_ID =:staffId");

		Query query = manager.createQuery(sql.toString());
		query.setParameter("staffId",staffId);

		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	public Long isExitCode(Long branchId, String code) {
		StringBuilder sql = new StringBuilder("select count(1) from ADMIN_STAFF staff");
		sql.append(" where staff.BRANCH_ID =:branchId");
		sql.append(" and upper(staff.CODE) =:code");

		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("branchId",branchId);
		query.setParameter("code",code.toUpperCase().trim());

		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	public List<AdminStaffDomain> getStaffByBranchForStorages(Long branchId, Long storagesId) {
		StringBuilder sql = new StringBuilder("select staff from AdminStaffDomain staff");
		sql.append(" where staff.deleted = :deleted");
		if (branchId != null) {
			sql.append(" and staff.branchId = :branchId");
		}
		sql.append(" and staff.id not in (");
		sql.append(" select sto.staffManageId from AdminStoragesDomain sto");
		sql.append(" where 1=1");
		if (branchId != null) {
			sql.append(" and sto.branchId = :branchId");
		}
		if (storagesId != null) {
			sql.append(" and sto.id != :storagesId");
		}
		sql.append(" )");
		sql.append(" order by staff.name");
		Query query = manager.createQuery(sql.toString());
		query.setParameter("deleted", AppConstant.DELETED.NO);
		if (branchId != null) {
			query.setParameter("branchId", branchId);
		}
		if (storagesId != null) {
			query.setParameter("storagesId", storagesId);
		}
		return query.getResultList();
	}

}