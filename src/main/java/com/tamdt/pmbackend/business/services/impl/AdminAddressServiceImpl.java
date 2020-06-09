package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminAddressDomain;
import com.tamdt.pmbackend.business.repositories.AdminAddressRepository;
import com.tamdt.pmbackend.business.services.AdminAddressService;
import com.tamdt.pmbackend.common.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminAddressServiceImpl extends BasicServiceImpl<AdminAddressDomain> implements AdminAddressService {

	@Autowired
	private AdminAddressRepository repo;

	@Override
	public BasicRepository<AdminAddressDomain> getRepository() {
		return repo;
	}

	@Override
	public List<AdminAddressDomain> getAllProvinces() {
		return repo.findByRank(AppConstant.ADDR_RANK.LEVEL1);
	}

	@Override
	public List<AdminAddressDomain> getDistrictByProvinceCode(String provinceCode) {
		return repo.findByProvinceCodeAndRank(provinceCode,AppConstant.ADDR_RANK.LEVEL2);
	}

	@Override
	public List<AdminAddressDomain> getWardByDistrictCode(String districtCode) {
		return repo.findByDistrictCodeAndRank(districtCode,AppConstant.ADDR_RANK.LEVEL3);
	}

}