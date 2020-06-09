package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.UsersSearchDTO;

import java.util.List;

public interface AdminUsersCustomRepository {
    void updateLoginStatus(Long userId, Long numberLogin);

    List<AdminUsersDomain> searchUsers(UsersSearchDTO searchDTO, String userName);

    Long countSearchUsers(UsersSearchDTO searchDTO, String userName);

    void deletedUsers(Long userId);

    void lockOrUnlock(Long userId, Long type);

    AdminUsersDomain getByEmail(String email);

    Long isExitUsername(String username);

    void deletedUsersByStaffId(Long staffId);
}