package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.dtos.CarsSearchDTO;

import java.util.List;

public interface AdminCarsCustomRepository {
    List<AdminCarsDomain> searchCars(CarsSearchDTO searchDTO);

    Long countSearchCars(CarsSearchDTO searchDTO);

    void deletedCars(Long id);

    Long isExitCode(String code);

    void deletedCarsByBranchId(Long branchId);
}