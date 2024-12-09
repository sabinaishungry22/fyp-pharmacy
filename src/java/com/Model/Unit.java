/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author USER
 */
public class Unit {
    private int id;
    private String unit_name;
    private String status;

    public Unit(int id, String unit_name, String status) {
        this.id = id;
        this.unit_name = unit_name;
        this.status = status;
    }

    public Unit(String unit_name, String status) {
        this.unit_name = unit_name;
        this.status = status;
    }

    public Unit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
   
}
