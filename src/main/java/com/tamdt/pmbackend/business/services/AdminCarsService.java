package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.dtos.CarsSearchDTO;
import com.tamdt.pmbackend.common.helper.ListJson;


public interface AdminCarsService extends BasicService<AdminCarsDomain> {
    ListJson<AdminCarsDomain> searchCars(CarsSearchDTO searchDTO);

    void deletedCars(Long id);

}