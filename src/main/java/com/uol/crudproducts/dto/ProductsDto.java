package com.uol.crudproducts.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.uol.crudproducts.model.Products;

public class ProductsDto {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;

	public ProductsDto() {
	}

	public ProductsDto(Products product) {
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
	
	public static List<ProductsDto> conversor(List<Products> products){
		List<ProductsDto> dtos = new ArrayList<ProductsDto>();
		for (Products product : products) {
			dtos.add(new ProductsDto(product));
		}
		return dtos;
	}
	
}
