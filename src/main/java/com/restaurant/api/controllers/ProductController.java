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

import com.restaurant.api.models.Category;
import com.restaurant.api.models.Product;
import com.restaurant.api.service.CategoryService;
import com.restaurant.api.service.ProductService;

@Controller
@RequestMapping(value = "/products")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService _productService;
	@Autowired
	CategoryService _categoryService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<?> getAllProducts(){
		
		List<Product> products = _productService.getAllProducts();
		if(products == null || products.isEmpty()) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<?> saveProduct(@RequestBody Product product){
		if(product == null || product.getNameProduct()== null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		if(product.getCategory().getIdCategory() == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		Category category = _categoryService.findCategoryById(product.getCategory().getIdCategory());
		product.setCategory(category);
		_productService.saveProduct(product);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> findProductById(@PathVariable(name = "id") Long idProduct){
		if(idProduct== null || idProduct<=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		Product product = _productService.findProductById(idProduct);
		
		if(product == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PATCH,headers = "Accept=application/json")
	public ResponseEntity<?> updateProductById(@PathVariable(name = "id") Long idProduct,@RequestBody Product product){
		if(idProduct== null || idProduct<=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		if(product == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); 
		}
		
		
		Product currentProduct= _productService.findProductById(idProduct);
		if(currentProduct == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		
		
		currentProduct.setNameProduct(product.getNameProduct());
		currentProduct.setDescription(product.getDescription());
		currentProduct.setAvailableProduct(product.isAvailableProduct());
		currentProduct.setCategory(product.getCategory());
		currentProduct.setPriceProduct(product.getPriceProduct());
		_productService.updateProduct(currentProduct);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") Long idProduct){
		if(idProduct == null || idProduct <=0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		}
		Product currentProduct= _productService.findProductById(idProduct);
		if(currentProduct == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
		}
		
		_productService.deleteProductById(idProduct);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
	}
	
	
}
