package com.restaurant.api.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.restaurant.api.models.Category;
import com.restaurant.api.models.Product;
import com.restaurant.api.payload.MessageResponse;
import com.restaurant.api.service.CategoryService;
import com.restaurant.api.service.ProductService;

@Controller
@RequestMapping(value = "/api/products")
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
	
	public static final String PRODUCT_IMAGES_FOLDER = "images/products";
	
	@RequestMapping( value = "/{id}/image",method = RequestMethod.POST,headers=("content-type=multipart/form-data"))
	public ResponseEntity<?> asignImage(@PathVariable(name = "id") Long id,
										@RequestParam(value = "file") MultipartFile multipartFile,
										UriComponentsBuilder uriComponentsBuilder){
		if(id == null || id <= 0 ) {
			return new ResponseEntity<>(new MessageResponse("The id product is required"),HttpStatus.BAD_REQUEST);
		}
		if(multipartFile.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse("The image is required"),HttpStatus.NO_CONTENT);
		}
		Product product = _productService.findProductById(id);
		if(product == null) {
			return new ResponseEntity<>(new MessageResponse("The product does NOT exist"),HttpStatus.NOT_FOUND);
		}
		
		if(product.getImage() != null) {
			String fileName = product.getImage();
			Path path = Paths.get(PRODUCT_IMAGES_FOLDER + fileName);
			File file = path.toFile();
			if(file.exists()) {
				file.delete();
			}
		}
		
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			String dateName = dateFormat.format(date);
			
			String fileName = String.format("%d-product-image-%s.%s", id,dateName,multipartFile.getContentType().split("/")[1]);
			product.setImage(fileName);
			
			Path path = Paths.get(PRODUCT_IMAGES_FOLDER + fileName);
			byte[] image = multipartFile.getBytes();
			Files.write(path, image);
			_productService.updateProduct(product);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
			
		}catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse("A error uploading the image: " + e.getStackTrace().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping( value = "/{id}/image",method = RequestMethod.GET)
	public ResponseEntity<?> getProductImage(@PathVariable(name = "id") Long id){
		if(id == null || id <= 0 ) {
			return new ResponseEntity<>(new MessageResponse("The id product is required"),HttpStatus.BAD_REQUEST);
		}
		Product product = _productService.findProductById(id);
		if(product == null) {
			return new ResponseEntity<>(new MessageResponse("The product does NOT exist"),HttpStatus.NOT_FOUND);
		}
		if(product.getImage() == null || product.getImage().isEmpty()) {
			return new ResponseEntity<>(new MessageResponse("Image not found"),HttpStatus.NOT_FOUND);
		}
		try {
			String fileName = product.getImage();
			Path path = Paths.get(PRODUCT_IMAGES_FOLDER + fileName);
			File file = path.toFile();
			if(!file.exists()) {
				return new ResponseEntity<>(new MessageResponse("Image does NOT exist"),HttpStatus.NOT_FOUND);
			}
			
			byte[] image = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
			
			
		}catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse("A error with image: " + e.getStackTrace().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
