package com.restaurant.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.restaurant.api.models.User;
import com.restaurant.api.service.UserService;

@Controller
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService _userService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(){
		
		List<User> users = _userService.getAllUsers();
		
		if(users == null || users.isEmpty()) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<?> saveUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder){
		
		/*if(user == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		_userService.saveUser(user);
*/
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> findUserById(@PathVariable(name = "id") Long idUser){
		if(idUser == null || idUser <=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		User user = _userService.findUserById(idUser);
		
		if(user == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PATCH,headers = "Accept=application/json")
	public ResponseEntity<?> updateUserById(@PathVariable(name = "id") Long idUser,@RequestBody User user){
		if(idUser == null || idUser <=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		if(user == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); 
		}
		
		
		User currentUser = _userService.findUserById(idUser);
		if(currentUser == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		
		
		currentUser.setNameUser(user.getNameUser());
		currentUser.setEmailUser(user.getEmailUser());
		currentUser.setPasswordUser(user.getPasswordUser());
		currentUser.setProfileUser(user.getProfileUser());
		currentUser.setRoles(user.getRoles());
		_userService.updateUser(currentUser);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long idUser){
		if(idUser == null || idUser <=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		User currentUser = _userService.findUserById(idUser);
		if(currentUser == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		_userService.deleteUserById(idUser);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
	}
	
}
