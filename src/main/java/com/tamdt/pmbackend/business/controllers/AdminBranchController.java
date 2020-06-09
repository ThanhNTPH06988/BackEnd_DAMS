package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.dtos.BranchSearchDTO;
import com.tamdt.pmbackend.business.services.AdminAtmService;
import com.tamdt.pmbackend.business.services.AdminBranchService;
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
@RequestMapping("/api/adminBranch")
public class AdminBranchController extends BasicController<AdminBranchDomain> {

    Gson gson = new Gson();
    @Autowired
    private AdminBranchService service;
	private AdminAtmService atmService;
    @Override
    public BasicService getService() {
        return service;
    }

    @RequestMapping(value = "/getAllBranch", method = RequestMethod.GET)
    public final ResponseEntity<?> getAllBranch() {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            value.put("list", service.getAllBranch());
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchBranch", method = RequestMethod.GET)
    public final ResponseEntity<?> searchBranch(@RequestParam String searchDTO) {

        BranchSearchDTO dtoSearch = gson.fromJson(searchDTO, BranchSearchDTO.class);

        ListJson<AdminBranchDomain> lstRs = new ListJson<>();
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

        try {
            lstRs = service.searchBranch(dtoSearch);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBranch", method = RequestMethod.POST)
    public final ResponseEntity<?> deleteBranch(@RequestParam Long branchId) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            service.deleteBranch(branchId);
            value.put("data", 1L);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBranchByStatusAndDeleted", method = RequestMethod.GET)
    public final ResponseEntity<?> getBranchByStatusAndDeleted() {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        List<AdminBranchDomain> lstBranch = new ArrayList<>();
        try {
            lstBranch = service.getAllBranch();
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstBranch).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/lockOrUnlock", method = RequestMethod.POST)
    public final ResponseEntity<?> lockOrUnlock(@RequestParam Long branchId, @RequestParam Long type) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<>();
        try {
            service.lockOrUnlock(branchId, type);
            value.put("data", 1L);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);

    }

    @RequestMapping(value = "/updateStaffId", method = RequestMethod.POST)
    public final ResponseEntity<?> updateStaffId(@RequestParam String staffCode,
                                                 @RequestParam Long branchId) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            service.updateStaffId(staffCode, branchId);
            value.put("data", 1L);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }
}