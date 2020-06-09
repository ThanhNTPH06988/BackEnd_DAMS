package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.dtos.AtmSearchDTO;
import com.tamdt.pmbackend.business.services.AdminAtmService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/adminAtm")
public class AdminAtmController extends BasicController<AdminAtmDomain> {

	Gson gson = new Gson();

	@Autowired
	private AdminAtmService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/deletedAtm",method = RequestMethod.POST)
	public final ResponseEntity<?> deletedAtm(@RequestParam Long atmId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			service.deletedAtm(atmId);
			value.put("data", 1L);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchAtms", method = RequestMethod.GET)
	public final ResponseEntity<?> searchAtms(@RequestParam String searchDTO) {
		AtmSearchDTO dtoSearch =  gson.fromJson(searchDTO, AtmSearchDTO.class);

		ListJson<AdminAtmDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs =  service.searchAtms(dtoSearch);
		} catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}

		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/lockOrUnlock", method = RequestMethod.POST)
	public  final ResponseEntity<?> lockOrUnlock(@RequestParam Long atmId,@RequestParam Long type){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<>();
		try {
			service.lockOrUnlock(atmId,type);
			value.put("data", 1L);
		}catch (Exception e){
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return  new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	// huongmk dung phan nay
	@RequestMapping(value = "/getAtmByBranchCode", method = RequestMethod.GET)
	public final ResponseEntity<?> getAtmByBranchCode(@RequestParam String branchCode) {
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
 			List<AdminAtmDomain> lstAtm = new ArrayList<>();
		try {
			lstAtm = service.getAtmByBranchCode(branchCode);
		} catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new Envelope(lstAtm).toResponseEntity(HttpStatus.OK);
	}

}
