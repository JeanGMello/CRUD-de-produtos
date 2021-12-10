package com.uol.crudproducts.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	 // Metodo list com paginação
	// navegar entre paginas: http://localhost:9999/products?pagina=0&qnt=10
	@GetMapping
	public ResponseEntity<Page<ProductsDto>> list(@PageableDefault(page = 0, size = 10) Pageable paginacao){
		
		Page<Product> product = productRepository.findAll(paginacao);
		Page<ProductsDto> dtos = ProductsDto.conversor(product);
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
	public ResponseEntity<ProductsDto> findById(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()){
			return ResponseEntity.ok(new ProductsDto(optional.get()));
		}else if(optional.isEmpty()){
			throw new EntityNotFoundException("Product with id "+id+" not found");
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductsDto> update(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()){
			Product product = form.atualizar(id, productRepository);
			return ResponseEntity.ok(new ProductsDto(product));
		}else if(optional.isEmpty()){
			throw new EntityNotFoundException("Product with id "+id+" not found");
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductsDto> delete(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()){
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else if(optional.isEmpty()){
			throw new EntityNotFoundException("Product with id "+id+" not found");
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/search")
	public List<Product> findBySearch(@RequestParam(required = false, value = "q") String q, @RequestParam(required = false, value = "minPrice")Double minPrice, @RequestParam(required = false, value = "maxPrice") Double maxPrice){
		return this.productRepository.findBySearch(q, minPrice, maxPrice);
	}
	
}
