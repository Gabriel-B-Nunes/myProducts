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

@RestController
@RequestMapping("/products")
public class ProductController {
    //variable declaration
    private final ProductRepository repository;

    //dependency injection
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
        return new ResponseEntity(ProductMapper.toResponseDTO(product), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        repository.deleteById(productOpt.get().getId());
        return new ResponseEntity(ProductMapper.toDetailDTO(productOpt.get()), HttpStatus.OK);
    }

    @PutMapping(consumes= "application/json", path= "/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Long id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        repository.save(productOpt.get());
        return new ResponseEntity(ProductMapper.toDetailDTO(productOpt.get()), HttpStatus.OK);
    }
}