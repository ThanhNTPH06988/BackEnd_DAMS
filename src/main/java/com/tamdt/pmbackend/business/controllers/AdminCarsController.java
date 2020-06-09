package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.dtos.CarsSearchDTO;
import com.tamdt.pmbackend.business.services.AdminCarsService;
import com.tamdt.pmbackend.common.helper.Envelope;
import com.tamdt.pmbackend.common.helper.ExceptionCode;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/adminCars")
public class AdminCarsController extends BasicController<AdminCarsDomain> {

	Gson gson = new Gson();

	@Autowired
	private AdminCarsService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/searchCars", method = RequestMethod.GET)
	public final ResponseEntity<?> searchCars(@RequestParam String searchDTO) {
		CarsSearchDTO dtoSearch =  gson.fromJson(searchDTO, CarsSearchDTO.class);

		ListJson<AdminCarsDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs =  service.searchCars(dtoSearch);
		} catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}

		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/deletedCars",method = RequestMethod.POST)
	public final ResponseEntity<?> deletedCars(@RequestParam Long id){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try{
			service.deletedCars(id);
		}catch (Exception ex){
			value.put("errorMessage", ExceptionCode.buildMessage(ex));
		}

		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

}