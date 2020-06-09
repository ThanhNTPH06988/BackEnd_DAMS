package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.domains.AdminRolesDomain;
import com.tamdt.pmbackend.business.dtos.MenusSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminMenuCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminMenuRepositoryImpl implements AdminMenuCustomRepository {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<AdminMenuDomain> getMenuForRoles(List<String> lstRoles,Long type,Long menuParentId) {


		StringBuilder sql = new StringBuilder("select DISTINCT amenu.*");
		sql.append(" from ADMIN_MENU amenu");
		sql.append(" inner join ADMIN_MENU_ROLE menurole on amenu.id = menurole.MENU_ID");
		sql.append(" where menurole.ROLE_CODE in (:lstRoles)");

		if(type == 1L)
			sql.append(" and amenu.menu_level =:menuLevel");
		else {
			sql.append(" and amenu.menu_level !=:menuLevel");
			sql.append(" and amenu.PARENT_ID =:parentId");
		}

		sql.append(" and amenu.STATUS =:status");
		sql.append(" and amenu.DELETED =:deleted");
		sql.append(" order by amenu.ORDER_INDEX ");

		Query query = manager.createNativeQuery(sql.toString(),"MenuMapping");
		query.setParameter("lstRoles",lstRoles);
		query.setParameter("menuLevel",AppConstant.MENU.LEVEL1);
		query.setParameter("status", AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);
		if(type == 0L)
			query.setParameter("parentId",menuParentId);

		return query.getResultList();
	}

	@Override
	public List<AdminMenuDomain> searchMenus(MenusSearchDTO searchDTO) {
		int firstRecord = searchDTO.getPage() * searchDTO.getSize();
		StringBuilder sql = new StringBuilder("select *");
		createStrQuery(sql,searchDTO);
		Query query = manager.createNativeQuery(sql.toString(),"MenuMapping");
		createQuery(query,searchDTO);
		query.setFirstResult(firstRecord);
		query.setMaxResults(searchDTO.getSize());
		return query.getResultList();
	}

	@Override
	public Long countSearchMenus(MenusSearchDTO searchDTO) {
		StringBuilder sql = new StringBuilder("select count(1)");
		createStrQuery(sql,searchDTO);
		Query query = manager.createNativeQuery(sql.toString());
		createQuery(query,searchDTO);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	public List<AdminMenuDomain> searchSubMenus(Long menuId,int page,int size) {

		int firstRecord = page * size;
		StringBuilder sql = new StringBuilder("select *");
		createStrQuerySubmenu(sql);

		Query query = manager.createNativeQuery(sql.toString(),"MenuMapping");
		createQuerySubmenu(query,menuId);
		query.setFirstResult(firstRecord);
		query.setMaxResults(size);

		return query.getResultList();
	}

	@Override
	public Long countSearchSubMenus(Long menuId) {
		StringBuilder sql = new StringBuilder("select count(1)");
		createStrQuerySubmenu(sql);

		Query query = manager.createNativeQuery(sql.toString());
		createQuerySubmenu(query,menuId);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}

	@Override
	public List<AdminRolesDomain> getMenusRoleNoGrant(Long menuId) {

		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from ADMIN_ROLES adRole");
		sql.append(" where 1=1");

		if (menuId != null && menuId > 0L) {
			sql.append(" and adRole.id not in (");
			sql.append(" select menuRole.role_id");
			sql.append(" from admin_menu_role menuRole");
			sql.append(" where menuRole.menu_id =:menuId");
			sql.append(")");
		}

		sql.append(" and adRole.status=:status");
		sql.append(" and adRole.deleted=:deleted");

		Query query = manager.createNativeQuery(sql.toString(),"RolesMapping");
		if(menuId != null && menuId > 0L)
			query.setParameter("menuId",menuId);
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);

		return query.getResultList();
	}

	@Override
	public List<AdminRolesDomain> getMenusRoleGrant(Long menuId) {

		StringBuilder sql = new StringBuilder("select distinct adRole.*");
		sql.append(" from ADMIN_ROLES adRole");
		sql.append(" inner join ADMIN_MENU_ROLE menuRole on adRole.id = menurole.role_id");
		sql.append(" where menurole.menu_id =:menuId");
		sql.append(" and adRole.status=:status");
		sql.append(" and adRole.deleted=:deleted");

		Query query = manager.createNativeQuery(sql.toString(),"RolesMapping");
		query.setParameter("menuId",menuId);
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void deletedMenu(Long menuId) {
		StringBuilder sql = new StringBuilder("update ADMIN_MENU set DELETED =:deleted");
		sql.append(" where id=:id");
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("deleted",AppConstant.DELETED.YES);
		query.setParameter("id",menuId);
		query.executeUpdate();
	}

	@Override
	public List<AdminRolesDomain> getUsersRoleNoGrant(Long userId) {

		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from ADMIN_ROLES adRole");
		sql.append(" where 1=1");

		if (userId != null && userId > 0L) {
			sql.append(" and adRole.id not in (");
			sql.append(" select userRole.ROLE_ID");
			sql.append(" from ADMIN_USER_ROLE userRole");
			sql.append(" where userRole.USER_ID =:userId");
			sql.append(")");
		}

		sql.append(" and adRole.status=:status");
		sql.append(" and adRole.deleted=:deleted");


		Query query = manager.createNativeQuery(sql.toString(),"RolesMapping");
		if(userId != null && userId > 0L)
			query.setParameter("userId",userId);
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);

		return query.getResultList();
	}

	@Override
	public List<AdminRolesDomain> getUsersRoleGrant(Long userId) {
		StringBuilder sql = new StringBuilder("select distinct adRole.*");
		sql.append(" from ADMIN_ROLES adRole");
		sql.append(" inner join ADMIN_USER_ROLE userRole on adRole.id = userRole.role_id");
		sql.append(" where userRole.USER_ID =:userId");
		sql.append(" and adRole.status=:status");
		sql.append(" and adRole.deleted=:deleted");

		Query query = manager.createNativeQuery(sql.toString(),"RolesMapping");
		query.setParameter("userId",userId);
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("deleted",AppConstant.DELETED.NO);

		return query.getResultList();
	}


	//============================================================================================
	private void createQuerySubmenu(Query query,Long menuId){
		query.setParameter("deleted",AppConstant.DELETED.NO);
		query.setParameter("status",AppConstant.STATUS.ACTIVE);
		query.setParameter("parentId",menuId);
		query.setParameter("menuLevel",AppConstant.MENU.LEVEL2);
	}
	private void createStrQuerySubmenu(StringBuilder sql){
		sql.append(" from admin_menu menu");
		sql.append(" where menu.deleted =:deleted");
		sql.append(" and menu.status =:status");
		sql.append(" and menu.parent_id =:parentId");
		sql.append(" and menu.menu_level =:menuLevel");
		sql.append(" order by menu.order_index ASC");
	}

	private void createQuery(Query query,MenusSearchDTO searchDTO){

		query.setParameter("deleted",AppConstant.DELETED.NO);
//		query.setParameter("menuLevel",AppConstant.MENU.LEVEL1);

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("code",StringUtils.toLikeString(searchDTO.getCode()));
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			query.setParameter("name",StringUtils.toLikeString(searchDTO.getName()));
		if(searchDTO.getMenuLevel() != null)
			query.setParameter("level",searchDTO.getMenuLevel());
		if(searchDTO.getStatus() != null)
			query.setParameter("status",searchDTO.getStatus());

	}

	private void createStrQuery(StringBuilder sql,MenusSearchDTO searchDTO){
		sql.append(" from admin_menu menu");
		sql.append(" where menu.deleted =:deleted");

		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" and lower(menu.code) like :code");
		if(!StringUtils.isNullOrEmpty(searchDTO.getCode()))
			sql.append(" and lower(menu.name) like :name");
		if(searchDTO.getMenuLevel() != null)
			sql.append(" and menu.menu_level =:level");
		if(searchDTO.getStatus() != null)
			sql.append(" and menu.status =:status");
		sql.append(" order by menu.id ASC");

//		sql.append(" connect by PRIOR menu.id = menu.parent_id");
//		sql.append(" start with menu.menu_level =:menuLevel");
	}

}