package com.emp.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.application.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
