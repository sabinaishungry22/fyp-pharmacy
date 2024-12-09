/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author USER
 */
public class Supplier {
    private int id;
    private String supplierName;
    private String address;
    private String mobileNo;
    private String email;
    private String city;
    private String state;
    private String zip;
    private double balance;

    // Getters and Setters

    public Supplier() {
    }

    public Supplier(int id, String supplierName, String address, String mobileNo, String email, String city, String state, String zip, double balance) {
        this.id = id;
        this.supplierName = supplierName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.balance = balance;
    }

    public Supplier(String supplierName, String address, String mobileNo, String email, String city, String state, String zip, double balance) {
        this.supplierName = supplierName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}

