package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.UsersDialogGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;


public interface AdminUsersService extends BasicService<AdminUsersDomain> {
    AdminUsersDomain getUserInfo(String username,String password);
    ListJson<AdminUsersDomain> searchUsers(UsersSearchDTO searchDTO);
    UsersRolesGrantDTO getUsersForRoles(Long userId);
    void deletedUsers(Long userId);
    void grantRoleForUser(UsersDialogGrantDTO roleGrantDTO);
    void lockOrUnlock(Long userId,Long type);
    void forgotPass(String email);

}