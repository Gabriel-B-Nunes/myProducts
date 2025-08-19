package com.example.myProducts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myProducts.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //query by product ID
    Optional<Product> findById(long id);

    //query by products brand
    List<Product> findByBrand(String brand);

}