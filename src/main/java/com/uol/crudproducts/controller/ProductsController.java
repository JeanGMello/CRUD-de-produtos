package com.uol.crudproducts.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<ProductsDto>> list(){
		List<Product> products = productRepository.findAll();
		List<ProductsDto> dtos = ProductsDto.conversor(products);
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	public ResponseEntity<ProductsDto> create(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder){
		Product product = form.conversor();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/producs/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductsDto(product));
	}
	
	@GetMapping("/{id}")
	public ProductsDto findById(@PathVariable Long id){
		Product product = productRepository.getOne(id);
		return new ProductsDto(product);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductsDto> update(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		Product product = form.atualizar(id, productRepository);
		return ResponseEntity.ok(new ProductsDto(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductsDto> delete(@PathVariable Long id){
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}













