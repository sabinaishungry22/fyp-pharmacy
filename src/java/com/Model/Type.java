/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author USER
 */
public class Type {
    private int id;
    private String type_name;
    private String status;

    public Type(int id, String type_name, String status) {
        this.id = id;
        this.type_name = type_name;
        this.status = status;
    }

    public Type(String type_name, String status) {
        this.type_name = type_name;
        this.status = status;
    }

    public Type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
