package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminStaffDomain;
import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.dtos.StoragesSearchDTO;
import com.tamdt.pmbackend.business.services.AdminStoragesService;
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
@RequestMapping("/api/adminStorages")
public class AdminStoragesController extends BasicController<AdminStoragesDomain> {

    Gson gson = new Gson();

    @Autowired
    private AdminStoragesService service;

    @Override
    public BasicService getService() {
        return service;
    }

    @RequestMapping(value = "/getStaff", method = RequestMethod.GET)
    public final ResponseEntity<?> getStaff() {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        List<AdminStaffDomain> lstRs = new ArrayList<>();
        try {
            lstRs = service.getStaff();
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/searchData", method = RequestMethod.GET)
    public final ResponseEntity<?> searchData(@RequestParam String searchDTO) {

        StoragesSearchDTO dtoSearch = gson.fromJson(searchDTO, StoragesSearchDTO.class);

        ListJson<AdminStoragesDomain> lstRs = new ListJson<>();
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

        try {
            lstRs = service.searchData(dtoSearch);
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getStorages", method = RequestMethod.GET)
    public final ResponseEntity<?> getStorages() {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        List<AdminStoragesDomain> lstSto = new ArrayList<>();
        try {
            lstSto = service.getStorages();
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstSto).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public final ResponseEntity<?> getStoragesStatusDeleted() {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        List<AdminStoragesDomain> lstSto = new ArrayList<>();
        try {
            lstSto = service.getStoragesStatusDeleted();
        } catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
            return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
        }
        return new Envelope(lstSto).toResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/lockOrUnlock", method = RequestMethod.POST)
    public  final ResponseEntity<?> lockOrUnlock(@RequestParam Long storId, @RequestParam Long type){
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<>();
        try {
            service.lockOrUnlock(storId,type);
            value.put("data",1L);
        }catch (Exception e){
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteStor", method = RequestMethod.POST)
    public final ResponseEntity<?> deleteStor(@RequestParam Long storId){
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<>();
        try{
            service.deleteStor(storId);
            value.put("data", 1L);
        }catch (Exception e){
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return  new ResponseEntity<Object>(value,HttpStatus.OK);
    }

}