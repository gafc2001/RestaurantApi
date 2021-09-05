package com.restaurant.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.restaurant.api.models.Category;
import com.restaurant.api.service.CategoryService;


@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired
	CategoryService _categoryService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<?> getAllCategories(){
		
		List<Category> categories = _categoryService.getAllCategories();
		
		/*if(categories == null || categories.isEmpty()) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}*/
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<?> saveCategory(@RequestBody Category category, UriComponentsBuilder uriComponentsBuilder){
		
		if(category == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		_categoryService.saveCategory(category);
		
		HttpHeaders header = new HttpHeaders();
		
		
		return new ResponseEntity<Category>(category,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> findCategoryById(@PathVariable(name = "id") Long idCategory){
		if(idCategory== null || idCategory<=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		Category category = _categoryService.findCategoryById(idCategory);
		
		if(category == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PATCH,headers = "Accept=application/json")
	public ResponseEntity<?> updateCategoryById(@PathVariable(name = "id") Long idCategory,@RequestBody Category category){
		if(idCategory== null || idCategory<=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		if(category == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); 
		}
		
		
		Category currentCategory= _categoryService.findCategoryById(idCategory);
		if(currentCategory == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		
		
		currentCategory.setNameCategory(category.getNameCategory());
		currentCategory.setDescription(category.getDescription());
		_categoryService.updateCategory(currentCategory);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") Long idCategory){
		if(idCategory == null || idCategory <=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		Category currentCategory= _categoryService.findCategoryById(idCategory);
		if(currentCategory == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		
		_categoryService.deleteCategoryById(idCategory);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
	}
}
