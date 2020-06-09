package com.tamdt.pmbackend.business.dtos;

import com.tamdt.pmbackend.business.domains.AdminRolesDomain;

import java.util.List;

public class UsersRolesGrantDTO {

    List<AdminRolesDomain> lstGrant;
    List<AdminRolesDomain> lstNoGrant;

    public List<AdminRolesDomain> getLstGrant() {
        return lstGrant;
    }

    public void setLstGrant(List<AdminRolesDomain> lstGrant) {
        this.lstGrant = lstGrant;
    }

    public List<AdminRolesDomain> getLstNoGrant() {
        return lstNoGrant;
    }

    public void setLstNoGrant(List<AdminRolesDomain> lstNoGrant) {
        this.lstNoGrant = lstNoGrant;
    }

}
