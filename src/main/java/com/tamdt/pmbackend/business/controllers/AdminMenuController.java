package com.tamdt.pmbackend.business.controllers;

import com.google.gson.Gson;
import com.tamdt.pmbackend.base.BasicController;
import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.dtos.MenusRolesGrantDTO;
import com.tamdt.pmbackend.business.dtos.MenusSearchDTO;
import com.tamdt.pmbackend.business.services.AdminMenuService;
import com.tamdt.pmbackend.common.helper.Envelope;
import com.tamdt.pmbackend.common.helper.ExceptionCode;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/adminMenu")
public class AdminMenuController extends BasicController<AdminMenuDomain> {

	Gson gson = new Gson();

	@Autowired
	private AdminMenuService service;

	@Override
	public BasicService getService() {
		return service;
	}

	@RequestMapping(value = "/getMenuForRoles",method = RequestMethod.GET)
	public final ResponseEntity<?> getMenuForRoles(
			@RequestParam List<String> lstRoles
	){

		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		List<AdminMenuDomain> lstMenu = new ArrayList<>();

		try {
			lstMenu = service.getMenuForRoles(lstRoles);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}

		return new Envelope(lstMenu).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/searchMenus",method = RequestMethod.GET)
	public final ResponseEntity<?> searchMenus(
			@RequestParam String searchDTO
	){
		MenusSearchDTO dtoSearch = gson.fromJson(searchDTO, MenusSearchDTO.class);

		ListJson<AdminMenuDomain> lstRs = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs = service.searchMenus(dtoSearch);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/getParentMenu",method = RequestMethod.GET)
	public final ResponseEntity<?> getParentMenu(){
		List<AdminMenuDomain> lstRs = new ArrayList<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstRs = service.getParentMenu();
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}

		return new Envelope(lstRs).toResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/menuRolesGrant",method = RequestMethod.GET)
	public final ResponseEntity<?> menuRolesGrant(@RequestParam Long menuId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try {
			MenusRolesGrantDTO role = service.menuRolesGrant(menuId);
			value.put("data", role);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
		}
		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}
	@RequestMapping(value = "/deletedMenu",method = RequestMethod.POST)
	public final ResponseEntity<?> deletedMenu(@RequestParam Long menuId){
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
		try{
			service.deletedMenu(menuId);
		}catch (Exception ex){
			value.put("errorMessage", ExceptionCode.buildMessage(ex));
		}

		return new ResponseEntity<Object>(value, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchSubMenus",method = RequestMethod.GET)
	public final ResponseEntity<?> searchSubMenus(
			@RequestParam Long menuId,
			@RequestParam int page,
			@RequestParam int size
	){
		ListJson<AdminMenuDomain> lstSubmenu = new ListJson<>();
		ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();

		try {
			lstSubmenu = service.searchSubMenus(menuId,page,size);
		}catch (Exception e) {
			value.put("errorMessage", ExceptionCode.buildMessage(e));
			return new ResponseEntity<Object>(value, HttpStatus.EXPECTATION_FAILED);
		}
		return new Envelope(lstSubmenu).toResponseEntity(HttpStatus.OK);
	}
}