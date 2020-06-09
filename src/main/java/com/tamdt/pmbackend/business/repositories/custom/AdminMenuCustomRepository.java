package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.domains.AdminRolesDomain;
import com.tamdt.pmbackend.business.dtos.MenusSearchDTO;

import java.util.List;

public interface AdminMenuCustomRepository {
    List<AdminMenuDomain> getMenuForRoles(List<String> lstRoles,Long type,Long menuParentId);

    List<AdminMenuDomain> searchMenus(MenusSearchDTO searchDTO);
    Long countSearchMenus(MenusSearchDTO searchDTO);

    List<AdminMenuDomain> searchSubMenus(Long menuId,int page,int size);
    Long countSearchSubMenus(Long menuId);

    List<AdminRolesDomain> getMenusRoleNoGrant(Long menuId);
    List<AdminRolesDomain> getMenusRoleGrant(Long menuId);

    void deletedMenu(Long menuId);

    //role for user
    List<AdminRolesDomain> getUsersRoleNoGrant(Long userId);
    List<AdminRolesDomain> getUsersRoleGrant(Long userId);
}