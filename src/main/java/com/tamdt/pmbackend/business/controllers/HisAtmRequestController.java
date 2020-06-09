package com.tamdt.pmbackend.business.controllers;

import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.HisAtmRequestDomain;
import com.tamdt.pmbackend.business.services.HisAtmRequestService;
import com.tamdt.pmbackend.common.helper.Envelope;
import com.tamdt.pmbackend.common.helper.ExceptionCode;
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
@RequestMapping("/api/hisAtmRequest")
public class HisAtmRequestController extends BasicController<HisAtmRequestDomain> {

    @Autowired
    private HisAtmRequestService service;

    @Override
    public BasicService getService() {
        return service;
    }

    @RequestMapping(value = "/getHisRequestByRequestId", method = RequestMethod.GET)
    public final ResponseEntity<?> getHisRequestByRequestId(@RequestParam Long requestId) {

        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        List<HisAtmRequestDomain> lstHis = new ArrayList<>();
        try {
            lstHis = service.getHisRequestByRequestId(requestId);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstHis).toResponseEntity(HttpStatus.OK);
    }
}