package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminAddressDomain;

import java.util.List;


public interface AdminAddressService extends BasicService<AdminAddressDomain> {
    List<AdminAddressDomain> getAllProvinces();
    List<AdminAddressDomain> getDistrictByProvinceCode(String provinceCode);
    List<AdminAddressDomain> getWardByDistrictCode(String districtCode);
}