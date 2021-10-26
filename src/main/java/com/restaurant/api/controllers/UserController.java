package com.restaurant.api.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.cloud.storage.Blob;
import com.restaurant.api.payload.response.SummaryReport;
import com.restaurant.api.repositories.UserRepository;
import com.restaurant.api.service.FirebaseService;
import com.restaurant.api.utils.ImageTools;
import com.restaurant.api.utils.SummaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.restaurant.api.models.User;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UserController{

	@Autowired
	FirebaseService firebaseService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public User getById(@PathVariable("id")Long id){
		return userRepository.findById(id).get();
	}

	@PatchMapping(value = "/{id}")
	public User updateUser(@PathVariable("id")Long id,@RequestBody User user){
		user.setIdUser(id);
		return userRepository.save(user);
	}

	public static final String USERS_IMAGES_FOLDER = "images/users/profile/";
	@RequestMapping( value = "/{id}/image",method = RequestMethod.GET)
	public ResponseEntity<?> getProfileImage(@PathVariable("id")Long id){
		User user = userRepository.findById(id).get();
		try{
			Blob blob;
			if(user.getProfileUser() == null){
				blob = firebaseService.getFile(USERS_IMAGES_FOLDER + "default-profile-image.jpg");
			}else{
				blob = firebaseService.getFile(USERS_IMAGES_FOLDER + user.getProfileUser());
			}
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(blob.getContent());
		}catch (FileNotFoundException e){
			System.out.println(e.getStackTrace());
		}catch (IOException io){
			System.out.println(io.getStackTrace());
		}
		return null;
	}

	@RequestMapping( value = "/{id}/image",method = RequestMethod.POST,headers=("content-type=multipart/form-data"))
	public ResponseEntity<?> saveProfileImage(@PathVariable("id")Long id, MultipartFile file){
		User user = userRepository.findById(id).get();
		if(user.getProfileUser() != null) {
			String fileName = user.getProfileUser();
			firebaseService.checkIfExists(USERS_IMAGES_FOLDER + fileName);
		}
		try{

			String fileName = firebaseService.generateFileName(id,"profile-image",file.getContentType().split("/")[1]);
			user.setProfileUser(fileName);
			byte[] image = ImageTools.resize(file.getBytes(),200,200);
			Blob blob = firebaseService.saveFile(image,USERS_IMAGES_FOLDER + fileName,"jpg");
			userRepository.save(user);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(blob.getContent());
		}catch (IOException e){
			System.out.println(e.getStackTrace());
		}
		return ResponseEntity.ok().body("");
	}

	@GetMapping(value = "/summary")
	@ResponseBody
	public SummaryReport getTotalUser(){
		List<Date> dates = SummaryUtils.getDates("DAY",true);
		Long countUsers = SummaryUtils.checkIfIsNull(userRepository.count());
		Long countNewUsers = SummaryUtils.checkIfIsNull(userRepository.countNewUsers(dates.get(1)));

		SummaryReport summary = new SummaryReport(countUsers,countUsers-countNewUsers,"users");
		
		return summary;
	}

	

	
}
