package com.restaurant.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.restaurant.api.models.RoleUser;
import com.restaurant.api.dto.response.MessageResponse;
import com.restaurant.api.service.RoleUserService;

@Controller
@RequestMapping(value = "/api/roles")
@CrossOrigin
public class RoleUserController {

	@Autowired 
	RoleUserService _rolesUserService;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ResponseEntity<?> getRoles(){
		List<RoleUser> roles = _rolesUserService.findAllRoles();
		if(roles.isEmpty() || roles == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoleUser>>(roles,HttpStatus.OK);
	}
	@RequestMapping(value = "/", method = RequestMethod.POST ,headers = "Accept=application/json")
	public ResponseEntity<?> saveRoles(@RequestBody RoleUser roleUser,UriComponentsBuilder uriComponentsBuilder){
		if(roleUser == null) {
			return new ResponseEntity<>(new MessageResponse("Provide a role"), HttpStatus.NO_CONTENT);
		}
		if(roleUser.getNameRole().isEmpty() || roleUser.getNameRole() == null) {
			return new ResponseEntity<>(new MessageResponse("Provide a role name "),HttpStatus.BAD_REQUEST);
		}
		_rolesUserService.saveRoleUser(roleUser);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
