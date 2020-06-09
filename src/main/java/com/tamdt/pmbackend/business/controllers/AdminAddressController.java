package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminAddressDomain;
import com.tamdt.pmbackend.business.services.AdminAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adminAddress")
public class AdminAddressController extends BasicController<AdminAddressDomain> {

	@Autowired
	private AdminAddressService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/getAllProvinces",method = RequestMethod.GET)
	public List<AdminAddressDomain> getAllProvinces(){
		try{
			return service.getAllProvinces();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getDistrictByProvinceCode",method = RequestMethod.GET)
	public List<AdminAddressDomain> getDistrictByProvinceCode(@RequestParam String provinceCode){
		try{
			return service.getDistrictByProvinceCode(provinceCode);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getWardByDistrictCode",method = RequestMethod.GET)
	public List<AdminAddressDomain> getWardByDistrictCode(@RequestParam String districtCode){
		try{
			return service.getWardByDistrictCode(districtCode);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}