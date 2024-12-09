/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Stock {
    private int id;
    private String itemName;
    private String category;
    private int quantity;
    private double price;
    private LocalDate expiryDate;
    private String supplier;
    private String batchNumber;
    private String location;
    private LocalDate lastUpdated;
    private String status;

    public Stock() {
    }

    public Stock(int id, String itemName, String category, int quantity, double price, LocalDate expiryDate, String supplier, String batchNumber, String location, LocalDate lastUpdated, String status) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.supplier = supplier;
        this.batchNumber = batchNumber;
        this.location = location;
        this.lastUpdated = lastUpdated;
        this.status = status;
    }
    
    public Stock(String itemName, String category, int quantity, double price, LocalDate expiryDate, String supplier, String batchNumber, String location, LocalDate lastUpdated, String status) {
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.supplier = supplier;
        this.batchNumber = batchNumber;
        this.location = location;
        this.lastUpdated = lastUpdated;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   

}
