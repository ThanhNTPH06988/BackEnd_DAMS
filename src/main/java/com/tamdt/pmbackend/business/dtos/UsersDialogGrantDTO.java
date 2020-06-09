package com.tamdt.pmbackend.business.dtos;

import com.tamdt.pmbackend.business.domains.AdminRolesDomain;

import java.util.List;

public class UsersDialogGrantDTO {

    List<AdminRolesDomain> lstRoles;
    Long userId;

    public List<AdminRolesDomain> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<AdminRolesDomain> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
