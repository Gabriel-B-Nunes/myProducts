package com.example.myProducts.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.myProducts.DTOs.ProductCreateDTO;
import com.example.myProducts.DTOs.ProductUpdateDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Product implements Serializable {

    //variables
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate validityDate;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fabricationDate;

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int quantity;

    @Column
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate creationDate;

    @Column
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate changeDate;

    //constructors
    public Product() {}

    public Product(ProductCreateDTO produtoUpdateDTO) {
        this.description = produtoUpdateDTO.getDescription();
        this.validityDate = produtoUpdateDTO.getValidityDate();
        this.fabricationDate = produtoUpdateDTO.getFabricationDate();
        this.weight = produtoUpdateDTO.getWeight();
        this.brand = produtoUpdateDTO.getBrand();
        this.quantity = produtoUpdateDTO.getQuantity();
        this.creationDate = LocalDate.now();
    }

    public Product(Product product) {
        this.description = product.getDescription();
        this.validityDate = product.getValidityDate();
        this.fabricationDate = product.getFabricationDate();
        this.weight = product.getWeight();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
        this.creationDate = product.getCreationDate();
        this.changeDate = product.getChangeDate();
    }

    //methods
    public void updateProduct(ProductUpdateDTO produtoUpdateDTO) {
        this.description = produtoUpdateDTO.getDescription();
        this.validityDate = produtoUpdateDTO.getValidityDate();
        this.fabricationDate = produtoUpdateDTO.getFabricationDate();
        this.weight = produtoUpdateDTO.getWeight();
        this.brand = produtoUpdateDTO.getBrand();
        this.quantity = produtoUpdateDTO.getQuantity();
        this.changeDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"description\":" + description + ", \"validityDate\":" + validityDate
                + ", \"fabricationDate\":" + fabricationDate + ", \"weight\":" + weight + ", \"brand\":" + brand
                + ", \"quantity\":" + quantity + ", \"creationDate\":" + creationDate + ", \"changeDate\":" + changeDate + "}";
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public LocalDate getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(LocalDate fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

}