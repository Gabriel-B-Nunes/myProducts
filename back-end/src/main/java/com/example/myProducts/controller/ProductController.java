package com.example.myProducts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myProducts.DTOs.ProductCreateDTO;
import com.example.myProducts.DTOs.ProductDetailDTO;
import com.example.myProducts.DTOs.ProductResponseDTO;
import com.example.myProducts.DTOs.ProductUpdateDTO;
import com.example.myProducts.exceptions.ProductNotFoundException;
import com.example.myProducts.model.Product;
import com.example.myProducts.repository.ProductRepository;
import com.example.myProducts.service.ProductMapper;

import jakarta.validation.Valid;

import org.slf4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/products")
public class ProductController {

    //variables
    private final ProductRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //constructors
    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    //methods
    @GetMapping
    public ResponseEntity<?> findAllProducts() {
        List<Product> listProduct = repository.findAll();
        
        return new ResponseEntity(ProductDetailDTO.fromList(listProduct), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        return new ResponseEntity(ProductMapper.toDetailDTO(productOpt.get()), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<?> findByMarca(@PathVariable String brand) {
        List<Product> listProduct = repository.findByBrand(brand);
        
        return new ResponseEntity(ProductDetailDTO.fromList(listProduct), HttpStatus.OK);
    }

    @PostMapping(consumes= "application/json")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        Product product = new Product(productCreateDTO);
        repository.save(product);
        
        try {
            MDC.put("old", "null");
            MDC.put("new", product.toString());
            logger.info("Product created: {}", product.toString());
        } catch (Exception e) {
            logger.error("Error saving product: {}", e.getMessage());
        } finally {
            MDC.clear();
        }

        return new ResponseEntity(ProductMapper.toResponseDTO(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        repository.deleteById(productOpt.get().getId());

        try {
            MDC.put("old", productOpt.get().toString());
            MDC.put("new", "null");
            logger.info("Product deleted: {}", productOpt.get().toString());
        } catch (Exception e) {
            logger.error("Error deleting product: {}", e.getMessage());
        } finally {
            MDC.clear();
        }

        return new ResponseEntity(ProductMapper.toDetailDTO(productOpt.get()), HttpStatus.OK);
    }

    @PutMapping(consumes= "application/json", path= "/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Long id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        
        Product oldProduct = new Product(productOpt.get());
        productOpt.get().updateProduct(productUpdateDTO);
        repository.save(productOpt.get());

        try {
            MDC.put("old", oldProduct.toString());
            MDC.put("new", productOpt.get().toString());
            logger.info("Product updated: {}", productOpt.get().toString());
        } catch (Exception e) {
            logger.error("Error updating product: {}", e.getMessage());
        } finally {
            MDC.clear();
        }

        return new ResponseEntity(ProductMapper.toDetailDTO(productOpt.get()), HttpStatus.OK);
    }
    
}