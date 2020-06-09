package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.dtos.MenusRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.MenusSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;

import java.util.List;


public interface AdminMenuService extends BasicService<AdminMenuDomain> {
    List<AdminMenuDomain> getMenuForRoles(List<String> lstRoles);

    ListJson<AdminMenuDomain> searchMenus(MenusSearchDTO searchDTO);

    ListJson<AdminMenuDomain> searchSubMenus(Long menuId,int page,int size);

    List<AdminMenuDomain> getParentMenu();

    MenusRolesGrantDTO menuRolesGrant(Long menuId);

    void deletedMenu(Long menuId);
}