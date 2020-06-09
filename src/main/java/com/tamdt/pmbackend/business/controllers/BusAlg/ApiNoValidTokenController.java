package com.tamdt.pmbackend.business.controllers.BusAlg;


import com.tamdt.pmbackend.business.services.ApiNoValidTokenService;
import com.tamdt.pmbackend.common.helper.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/apiNoValidToken")
public class ApiNoValidTokenController {

    @Autowired
    private ApiNoValidTokenService service;

    @RequestMapping(value = "/forgotPass",method = RequestMethod.POST)
    public final ResponseEntity<?> forgotPass(@RequestParam String email){
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            service.forgotPass(email);
            value.put("data", 1L);
        }catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }
}
