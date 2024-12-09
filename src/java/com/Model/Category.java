/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author USER
 */
public class Category {
    private int id;
    private String category_name;
    private String status;

    public Category(int id, String category_name, String status) {
        this.id = id;
        this.category_name = category_name;
        this.status = status;
    }

    public Category(String category_name, String status) {
        this.category_name = category_name;
        this.status = status;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
