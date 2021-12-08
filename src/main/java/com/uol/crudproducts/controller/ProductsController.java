package com.uol.crudproducts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.crudproducts.dto.ProductsDto;
import com.uol.crudproducts.model.Products;
import com.uol.crudproducts.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<ProductsDto>> listAll(){
		List<Products> products = productRepository.findAll();
		List<ProductsDto> dtos = ProductsDto.conversor(products);
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ProductsDto>> getById(@PathVariable Long id){
		List<Products> products = productRepository.findById(id);
		List<ProductsDto> dtos = ProductsDto.conversor(products);
		return ResponseEntity.ok(dtos);
	}

	
	
}
