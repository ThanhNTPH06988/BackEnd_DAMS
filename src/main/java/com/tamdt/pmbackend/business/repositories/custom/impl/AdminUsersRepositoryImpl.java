package com.tamdt.pmbackend.business.repositories.custom.impl;

import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.UsersSearchDTO;
import com.tamdt.pmbackend.business.repositories.custom.AdminUsersCustomRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.ulti.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class AdminUsersRepositoryImpl implements AdminUsersCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public void updateLoginStatus(Long userId, Long numberLogin) {
        StringBuilder sql = new StringBuilder("update ADMIN_USERS set NUMBER_LOGIN =:numberLogin, LAST_LOGIN= sysdate");
        sql.append(" where ID=:id");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("id", userId);
        query.setParameter("numberLogin", numberLogin);
        query.executeUpdate();
    }

    @Override
    public List<AdminUsersDomain> searchUsers(UsersSearchDTO searchDTO, String userName) {

        int firstRecord = searchDTO.getPage() * searchDTO.getSize();
        StringBuilder sql = new StringBuilder("select users ");
        createStrQuery(sql, searchDTO);
        Query query = manager.createQuery(sql.toString());
        createQuery(query, searchDTO, userName);
        query.setFirstResult(firstRecord);
        query.setMaxResults(searchDTO.getSize());

        return query.getResultList();
    }

    @Override
    public Long countSearchUsers(UsersSearchDTO searchDTO, String userName) {

        StringBuilder sql = new StringBuilder("select count(users) ");
        createStrQuery(sql, searchDTO);
        Query query = manager.createQuery(sql.toString());
        createQuery(query, searchDTO, userName);
        return (Long) query.getSingleResult();
    }

    @Override
    @Transactional
    public void deletedUsers(Long userId) {
        StringBuilder sql = new StringBuilder("update ADMIN_USERS set DELETED=:deleted where ID=:usersId");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("deleted", AppConstant.DELETED.YES);
        query.setParameter("usersId", userId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void lockOrUnlock(Long userId, Long type) {
        StringBuilder sql = new StringBuilder("update ADMIN_USERS set STATUS=:status where id=:usersId");
        Query query = manager.createNativeQuery(sql.toString());
        if (type == AppConstant.STATUS.ACTIVE)
            query.setParameter("status", AppConstant.STATUS.INACTIVE);
        else
            query.setParameter("status", AppConstant.STATUS.ACTIVE);
        query.setParameter("usersId", userId);
        query.executeUpdate();
    }

    @Override
    public AdminUsersDomain getByEmail(String email) {

        StringBuilder sql = new StringBuilder("select adUser from AdminUsersDomain adUser");
        sql.append(" where adUser.deleted=:deleted and adUser.status=:status");
        sql.append(" and upper(adUser.staffDomain.email)=:email");

        Query query = manager.createQuery(sql.toString());
        query.setParameter("deleted", AppConstant.DELETED.NO);
        query.setParameter("status", AppConstant.STATUS.ACTIVE);
        query.setParameter("email", email.toUpperCase().trim());

        return (AdminUsersDomain) query.getSingleResult();
    }

    @Override
    public Long isExitUsername(String username) {

        StringBuilder sql = new StringBuilder("select count(1)");
        sql.append(" from ADMIN_USERS adUsers");
        sql.append(" where upper(adUsers.USER_NAME) =:username");

        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("username", username.toUpperCase().trim());

        return ((BigDecimal) query.getSingleResult()).longValue();
    }

    @Override
    @Transactional
    public void deletedUsersByStaffId(Long staffId) {
        StringBuilder sql = new StringBuilder("update ADMIN_USERS set DELETED = :deleted where STAFF_ID = :staffId");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("deleted", AppConstant.DELETED.YES);
        query.setParameter("staffId", staffId);
        query.executeUpdate();
    }


    private void createStrQuery(StringBuilder sql, UsersSearchDTO searchDTO) {
        sql.append(" from AdminUsersDomain users");
        sql.append(" where users.deleted =:deleted");

        if (!StringUtils.isNullOrEmpty(searchDTO.getUserName()))
            sql.append(" and lower(users.userName) like :userName");
        if (searchDTO.getDateFrom() != null)
            sql.append(" and TRUNC(users.dateCreated) >= TRUNC(:fromDate)");
        if (searchDTO.getDateTo() != null)
            sql.append(" and TRUNC(users.dateCreated) <= TRUNC(:toDate)");
        if (searchDTO.getStatus() != null)
            sql.append(" and users.status =:status");
        if (!StringUtils.isNullOrEmpty(searchDTO.getStaffName()))
            sql.append(" and lower(users.staffDomain.name) like :staffName");
        if (searchDTO.getStaffPositionId() != null)
            sql.append(" and users.staffDomain.positionId =:position");

        sql.append(" and lower(users.userName) !=:userNameLogin");
        sql.append(" order by users.dateCreated DESC");
    }

    private void createQuery(Query query, UsersSearchDTO searchDTO, String userName) {
        query.setParameter("deleted", AppConstant.DELETED.NO);
        if (!StringUtils.isNullOrEmpty(searchDTO.getUserName()))
            query.setParameter("userName", StringUtils.toLikeString(searchDTO.getUserName()));
        if (searchDTO.getDateFrom() != null)
            query.setParameter("fromDate", searchDTO.getDateFrom());
        if (searchDTO.getDateTo() != null)
            query.setParameter("toDate", searchDTO.getDateTo());
        if (searchDTO.getStatus() != null)
            query.setParameter("status", searchDTO.getStatus());
        if (!StringUtils.isNullOrEmpty(searchDTO.getStaffName()))
            query.setParameter("staffName", StringUtils.toLikeString(searchDTO.getStaffName()));
        if (searchDTO.getStaffPositionId() != null)
            query.setParameter("position", searchDTO.getStaffPositionId());

        query.setParameter("userNameLogin", userName.toLowerCase());
    }
}