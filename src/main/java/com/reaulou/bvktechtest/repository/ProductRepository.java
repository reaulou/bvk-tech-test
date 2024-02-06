package com.reaulou.bvktechtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reaulou.bvktechtest.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
