package com.uol.crudproducts.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.uol.crudproducts.model.Product;
import com.uol.crudproducts.repository.ProductRepository;

public class ProductForm {

	@NotNull @NotEmpty @Length(min = 3)
	private String name;
	@NotNull @NotEmpty @Length(min = 3)
	private String description;
	@NotNull
	private BigDecimal price;
	
	
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


	public Product conversor() {
		return new Product(name, description, price);
	}

	public Product atualizar(Long id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);
		return null;
	}
	
}
