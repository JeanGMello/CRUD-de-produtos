package com.uol.crudproducts.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.uol.crudproducts.model.Product;

public class ProductsDto {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;

	public ProductsDto() {
	}

	public ProductsDto(Product product) {
		super();
		this.id = product.getId();
		this.name =product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public static Page<ProductsDto> conversor(Page<Product> products){
		return products.map(ProductsDto::new);
	}
	
}
