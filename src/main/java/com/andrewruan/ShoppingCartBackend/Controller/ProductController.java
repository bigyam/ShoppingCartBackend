package com.andrewruan.ShoppingCartBackend.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrewruan.ShoppingCartBackend.Entities.Product;
import com.andrewruan.ShoppingCartBackend.Service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public Iterable<Product> getAllProducts(){
		return productService.getAll();
	}
	
	@GetMapping("/{prod_id}")
	public ResponseEntity<?> getProductById(@PathVariable Long prod_id){
		Product product = productService.getById(prod_id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult result){
		
		if(result.hasErrors()){
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		Product newProduct = productService.saveOrUpdateProduct(product);
		
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{prod_id}")
	public ResponseEntity<?> updateProduct(@Valid @PathVariable Long prod_id, @RequestBody Product product, BindingResult result){
		
		if(result.hasErrors()){
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		Product productStored =  productService.getById(prod_id);
		productStored.setDescription(product.getDescription());
		productStored.setName(product.getName());
		productStored.setPrice(product.getPrice());
		productStored.setQuantity(product.getQuantity());
		
		productService.saveOrUpdateProduct(productStored);
		
		return new ResponseEntity<Product>(productStored, HttpStatus.OK);
		
	}
}
