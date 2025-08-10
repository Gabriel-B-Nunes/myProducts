package com.example.myProducts.DTOs;

import java.time.LocalDate;

import com.example.myProducts.model.Product;

public class ProductResponseDTO {
    private String description;
    private LocalDate validityDate;
    private LocalDate fabricationDate;
    private int weight;
    private String brand;
    private int quantity;
    private LocalDate creationDate;
    private LocalDate changeDate;

    public ProductResponseDTO() {}

    public ProductResponseDTO(Product produto) {
        this.description = produto.getDescription();
        this.validityDate = produto.getValidityDate();
        this.fabricationDate = produto.getFabricationDate();
        this.weight = produto.getWeight();
        this.brand = produto.getBrand();
        this.quantity = produto.getQuantity();
        this.creationDate = produto.getCreationDate();
        this.changeDate = produto.getChangeDate();
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