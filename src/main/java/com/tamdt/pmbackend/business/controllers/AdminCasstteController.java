package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.dtos.CasstteSearchDTO;
import com.tamdt.pmbackend.business.services.AdminCasstteService;
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
@RequestMapping("/api/adminCasstte")
public class AdminCasstteController extends BasicController<AdminCasstteDomain> {

	Gson gson = new Gson();

	@Autowired
	private AdminCasstteService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/deletedCasstte",method = RequestMethod.POST)
	public final ResponseEntity<?> deletedCasstte(@RequestParam Long cassId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			service.deletedCasstte(cassId);
			value.put("data", 1L);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchCasstte", method = RequestMethod.GET)
	public final ResponseEntity<?> searchCasstte(@RequestParam String searchDTO) {
		CasstteSearchDTO dtoSearch =  gson.fromJson(searchDTO, CasstteSearchDTO.class);

		ListJson<AdminCasstteDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs =  service.searchCasstte(dtoSearch);
		} catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}

		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}


}