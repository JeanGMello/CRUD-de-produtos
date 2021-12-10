package com.uol.crudproducts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uol.crudproducts.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT a.* FROM products a WHERE"
	+ "(:q IS NULL OR (UPPER(name)"
	+ "LIKE UPPER(CONCAT('%', :q, '%'))"
	+ "OR UPPER(description)"
	+ "LIKE UPPER(CONCAT('%', :q, '%'))))"
	+ "AND (:minPrice IS NULL OR price >= :minPrice)"
	+ "AND (:maxPrice IS NULL OR price <= :maxPrice)", nativeQuery = true)
	List<Product> findBySearch(String q, Double minPrice, Double maxPrice);
	
}
