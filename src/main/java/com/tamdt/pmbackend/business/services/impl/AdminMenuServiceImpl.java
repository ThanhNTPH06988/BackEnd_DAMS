package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.domains.AdminMenuRoleDomain;
import com.tamdt.pmbackend.business.domains.AdminRolesDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.MenusRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.MenusSearchDTO;
import com.tamdt.pmbackend.business.dtos.UsersRolesGrantDTO;
import com.tamdt.pmbackend.business.repositories.AdminMenuRepository;
import com.tamdt.pmbackend.business.repositories.AdminMenuRoleRepository;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.business.services.AdminMenuService;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminMenuServiceImpl extends BasicServiceImpl<AdminMenuDomain> implements AdminMenuService {

    @Autowired
    private AdminMenuRepository repo;

    @Autowired
    private AdminMenuRoleRepository menuRoleRepo;

    @Autowired
    private AdminUsersRepository userRepo;

    @Override
    public BasicRepository<AdminMenuDomain> getRepository() {
        return repo;
    }

    @Override
    public List<AdminMenuDomain> getMenuForRoles(List<String> lstRoles) {

        List<AdminMenuDomain> lstMenu = repo.getMenuForRoles(lstRoles,1L,null);

        for (AdminMenuDomain menu : lstMenu) {
//            List<AdminMenuDomain> subMenu = repo.findByParentIdAndMenuLevelAndStatusAndDeletedOrderByOrderIndexAsc(menu.getId(),AppConstant.MENU.LEVEL2,AppConstant.STATUS.ACTIVE,AppConstant.DELETED.NO);
            List<AdminMenuDomain> subMenu = repo.getMenuForRoles(lstRoles,0L,menu.getId());
            menu.setLstSubmenu(subMenu);
        }

        return lstMenu;
    }

    @Override
    public ListJson<AdminMenuDomain> searchMenus(MenusSearchDTO searchDTO) {

        List<AdminMenuDomain> lstRs = new ArrayList<>();
        Long countSearch = repo.countSearchMenus(searchDTO);

        if(countSearch > 0L){
            lstRs = repo.searchMenus(searchDTO);
        }

        return new ListJson<AdminMenuDomain>(lstRs,(long)countSearch);
    }

    @Override
    public ListJson<AdminMenuDomain> searchSubMenus(Long menuId, int page, int size) {

        Long coutSearch = repo.countSearchSubMenus(menuId);
        List<AdminMenuDomain> lstRs = new ArrayList<>();

        if(coutSearch > 0L){
            lstRs = repo.searchSubMenus(menuId,page,size);
        }

        return new ListJson<AdminMenuDomain>(lstRs,(long)coutSearch);
    }

    @Override
    public List<AdminMenuDomain> getParentMenu() {
        return repo.findByDeletedAndStatusAndMenuLevel(AppConstant.DELETED.NO,AppConstant.STATUS.ACTIVE,AppConstant.MENU.LEVEL1);
    }

    @Override
    public MenusRolesGrantDTO menuRolesGrant(Long menuId) {

        MenusRolesGrantDTO roles = new MenusRolesGrantDTO();

        List<AdminRolesDomain> lstNoGrant = repo.getMenusRoleNoGrant(menuId);
        roles.setLstNoGrant(lstNoGrant);
        if(menuId != null && menuId>0L){
            List<AdminRolesDomain> lstGrant = repo.getMenusRoleGrant(menuId);
            roles.setLstGrant(lstGrant);
        }

        return roles;
    }

    @Override
    public void deletedMenu(Long menuId) {
        repo.deletedMenu(menuId);
    }

    @Override
    @Transactional
    public AdminMenuDomain saveOrUpdate(AdminMenuDomain adminMenuDomain) {

        AdminUsersDomain userLogin = userRepo.findByUserNameAndDeleted(IContext.getUserNameLogin(),AppConstant.DELETED.NO);

        //menu cha
        if(adminMenuDomain.getId() == null){
            adminMenuDomain.setDateCreated(new Date());
            adminMenuDomain.setStatus(AppConstant.STATUS.ACTIVE);
            adminMenuDomain.setDeleted(AppConstant.DELETED.NO);


            if(adminMenuDomain.getParentId() != null)
                adminMenuDomain.setMenuLevel(AppConstant.MENU.LEVEL2);
            else
                adminMenuDomain.setMenuLevel(AppConstant.MENU.LEVEL1);
            adminMenuDomain.setUserId(userLogin.getId());
        }




        super.saveOrUpdate(adminMenuDomain);

        //deleted role befor insert
        menuRoleRepo.deleteByMenuId(adminMenuDomain.getId());

        for(AdminRolesDomain role : adminMenuDomain.getLstRoles()){
            AdminMenuRoleDomain menuRole = new AdminMenuRoleDomain();
            menuRole.setMenuId(adminMenuDomain.getId());
            menuRole.setRoleId(role.getId());
            menuRole.setRoleCode(role.getCode());
            menuRole.setDateCreated(new Date());
            menuRole.setUserId(userLogin.getId());
            menuRoleRepo.save(menuRole);
        }


        return adminMenuDomain;
    }
}