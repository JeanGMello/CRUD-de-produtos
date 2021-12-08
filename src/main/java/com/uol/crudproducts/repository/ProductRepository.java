package com.uol.crudproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uol.crudproducts.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}