package com.example.myProducts.service;

import com.example.myProducts.DTOs.ProductCreateDTO;
import com.example.myProducts.DTOs.ProductDetailDTO;
import com.example.myProducts.DTOs.ProductResponseDTO;
import com.example.myProducts.model.Product;

public class ProductMapper {

    //methods
    public static Product toEntity(ProductCreateDTO productCreateDTO) {
        return new Product(productCreateDTO);
    }

    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(product);
    }

    public static ProductDetailDTO toDetailDTO(Product product) {
        return new ProductDetailDTO(product);
    }

}