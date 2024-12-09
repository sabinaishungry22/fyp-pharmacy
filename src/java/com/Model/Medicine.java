package com.Model;

import java.time.LocalDate;

public class Medicine {
    private int id;
    private String medicineName;
    private int categoryId;
    private int unitId;
    private int typeId;
    private int quantity;
    private double price;
    private LocalDate expiryDate;

    // Constructors
    public Medicine() {}

    public Medicine(String medicineName, int categoryId, int unitId, int typeId, int quantity, double price, LocalDate expiryDate) {
        this.medicineName = medicineName;
        this.categoryId = categoryId;
        this.unitId = unitId;
        this.typeId = typeId;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public Medicine(int id, String medicineName, int categoryId, int unitId, int typeId, int quantity, double price, LocalDate expiryDate) {
        this.id = id;
        this.medicineName = medicineName;
        this.categoryId = categoryId;
        this.unitId = unitId;
        this.typeId = typeId;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
