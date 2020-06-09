package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.dtos.RequestSearchDTO;
import com.tamdt.pmbackend.business.services.AtmRequestService;
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
@RequestMapping("/api/atmRequest")
public class AtmRequestController extends BasicController<AtmRequestDomain> {

	Gson gson = new Gson();

	@Autowired
	private AtmRequestService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/searchData", method = RequestMethod.GET)
	public final ResponseEntity<?> searchData(@RequestParam String searchDTO) {

		RequestSearchDTO dtoSearch = gson.fromJson(searchDTO, RequestSearchDTO.class);

		ListJson<AtmRequestDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs = service.searchData(dtoSearch);
		} catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}
}