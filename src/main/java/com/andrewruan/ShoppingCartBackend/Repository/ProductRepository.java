package com.andrewruan.ShoppingCartBackend.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andrewruan.ShoppingCartBackend.Entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Product getById (Long id);
}
