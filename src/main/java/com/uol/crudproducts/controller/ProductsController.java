package com.uol.crudproducts.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.uol.crudproducts.dto.ProductsDto;
import com.uol.crudproducts.form.ProductForm;
import com.uol.crudproducts.model.Product;
import com.uol.crudproducts.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<ProductsDto>> listAll(){
		List<Product> products = productRepository.findAll();
		List<ProductsDto> dtos = ProductsDto.conversor(products);
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	public ResponseEntity<ProductsDto> cadastrar(@RequestBody ProductForm form, UriComponentsBuilder uriBuilder){
		Product product = form.conversor();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/producs/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductsDto(product));
	}
	
	
}













