package com.andrewruan.ShoppingCartBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrewruan.ShoppingCartBackend.Entities.Product;
import com.andrewruan.ShoppingCartBackend.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product saveOrUpdateProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	public Iterable<Product> getAll(){
		return productRepository.findAll();
	}
	
	public Product getById(Long id) {
		return productRepository.getById(id);
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
