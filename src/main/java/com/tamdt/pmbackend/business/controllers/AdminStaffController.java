package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.dtos.StaffSearchDTO;
import com.tamdt.pmbackend.business.services.AdminStaffService;
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
@RequestMapping("/api/adminStaff")
public class AdminStaffController extends BasicController<AdminStaffDomain> {
    Gson gson = new Gson();

    @Autowired
    private AdminStaffService service;

    @Override
    public BasicService getService() {
        return service;
    }

    @RequestMapping(value = "/staffByBranch", method = RequestMethod.GET)
    public final ResponseEntity<?> getStaffByBranch(@RequestParam Long branchId) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            value.put("list", service.getStaffByBranchId(branchId));
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchDataStaff", method = RequestMethod.GET)
    public final ResponseEntity<?> searchStaff(@RequestParam String searchDTO) {


        StaffSearchDTO dtoSearch = gson.fromJson(searchDTO, StaffSearchDTO.class);

        ListJson<AdminStaffDomain> lstRs = new ListJson<>();
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

        try {
            lstRs = service.searchStaff(dtoSearch);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteStaff", method = RequestMethod.POST)
    public final ResponseEntity<?> deleteStaff(@RequestParam Long userId) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            service.deleteStaff(userId);
            value.put("data", 1L);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/getStaffByBranchId", method = RequestMethod.GET)
    public final ResponseEntity<?> getStaffByBranchId(@RequestParam Long branchId) {

        List<AdminStaffDomain> lstStaff = new ArrayList<>();
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

        try {
            lstStaff = service.getStaffByBranchId(branchId);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstStaff).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/lockOrUnlock", method = RequestMethod.POST)
    public final ResponseEntity<?> lockOrUnlock(@RequestParam Long staffId, @RequestParam Long type) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            service.lockOrUnlock(staffId, type);
            value.put("data", 1L);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

	@RequestMapping(value = "/isExitStaffInUsers",method = RequestMethod.GET)
	public final ResponseEntity<?> isExitStaffInUsers(@RequestParam Long staffId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try{
			value.put("data",service.getStaffByBranchId(staffId));
		}catch (Exception e){
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

    @RequestMapping(value = "/getStaffByBranchForStorages", method = RequestMethod.GET)
    public final ResponseEntity<?> getStaffByBranchForStorages(@RequestParam Long branchId,
                                                               @RequestParam Long storagesId) {

        List<AdminStaffDomain> lstStaff = new ArrayList<>();
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

        try {
            lstStaff = service.getStaffByBranchForStorages(branchId, storagesId);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstStaff).toResponseEntity(HttpStatus.OK);
    }

}