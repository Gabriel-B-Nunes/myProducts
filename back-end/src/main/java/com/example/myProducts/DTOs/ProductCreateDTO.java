package com.example.myProducts.DTOs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductCreateDTO {
    
    @NotBlank(message= "Description cannot be blank")
    private String description;

    @NotNull(message= "ValidityDate cannot be null")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate validityDate;

    @NotNull(message= "FabricationDate cannot be null")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fabricationDate;

    @NotNull(message= "Weight cannot be null")
    @Positive(message= "Weight cannot be negative")
    private int weight;

    @NotBlank(message= "Brand cannot be blank")
    private String brand;

    @NotNull(message= "Quantity cannot be null")
    @Positive(message= "Quantity cannot be negative")
    private int quantity;

    public ProductCreateDTO() {}

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

}