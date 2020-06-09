package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminRolesDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.UsersDialogGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.UsersSearchDTO;
import com.tamdt.pmbackend.business.services.AdminUsersService;
import com.tamdt.pmbackend.common.helper.Envelope;
import com.tamdt.pmbackend.common.helper.ExceptionCode;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/adminUsers")
public class AdminUsersController extends BasicController<AdminUsersDomain> {

	Gson gson = new Gson();

	@Autowired
	private AdminUsersService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
	public final ResponseEntity<?> getUserInfo(@RequestParam String username, @RequestParam String password){

		AdminUsersDomain usersDomain;

		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try{
			usersDomain = service.getUserInfo(username,password);
			value.put("data",usersDomain);
		}catch (Exception e){
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchUsers",method = RequestMethod.GET)
	public final ResponseEntity<?> searchUsers(@RequestParam String searchDTO){

		UsersSearchDTO dtoSearch = gson.fromJson(searchDTO, UsersSearchDTO.class);

		ListJson<AdminUsersDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs = service.searchUsers(dtoSearch);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/getUsersForRoles",method = RequestMethod.GET)
	public final ResponseEntity<?> getUsersForRoles(@RequestParam Long userId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			UsersRolesGrantDTO role = service.getUsersForRoles(userId);
			value.put("data", role);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletedUsers",method = RequestMethod.POST)
	public final ResponseEntity<?> deletedUsers(@RequestParam Long userId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			service.deletedUsers(userId);
			value.put("data", 1L);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/grantRoleForUser",method = RequestMethod.POST)
	public final ResponseEntity<?> grantRoleForUser(@RequestParam String grantDTO){

		UsersDialogGrantDTO roleGrantDTO = gson.fromJson(grantDTO, UsersDialogGrantDTO.class);

		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			service.grantRoleForUser(roleGrantDTO);
			value.put("data", 1L);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/lockOrUnlock",method = RequestMethod.POST)
	public final ResponseEntity<?> lockOrUnlock(@RequestParam Long userId,@RequestParam Long type){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			service.lockOrUnlock(userId,type);
			value.put("data", 1L);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

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