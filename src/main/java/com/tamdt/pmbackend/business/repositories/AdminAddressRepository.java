package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminAddressDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminAddressCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAddressRepository extends BasicRepository<AdminAddressDomain>, AdminAddressCustomRepository {
    List<AdminAddressDomain> findByRank(Long rankId);
    List<AdminAddressDomain> findByProvinceCodeAndRank(String provinceCode,Long rankId);
    List<AdminAddressDomain> findByDistrictCodeAndRank(String districtCode,Long rankId);
}
