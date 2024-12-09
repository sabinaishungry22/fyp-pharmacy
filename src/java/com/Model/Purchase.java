package com.Model;

import java.math.BigDecimal;
import java.sql.Date;

public class Purchase {
    private int id;
    private int supplierId;
    private String batchId;
    private Date expiryDate;
    private BigDecimal stockQty;
    private String boxPattern;
    private int boxQty;
    private int quantity;
    private BigDecimal supplierPrice;
    private BigDecimal totalPurchasePrice;

    // Constructor including totalPurchasePrice (for reading from database)
    public Purchase(int id, int supplierId, String batchId, Date expiryDate, BigDecimal stockQty, String boxPattern, int boxQty, int quantity, BigDecimal supplierPrice, BigDecimal totalPurchasePrice) {
        this.id = id;
        this.supplierId = supplierId;
        this.batchId = batchId;
        this.expiryDate = expiryDate;
        this.stockQty = stockQty;
        this.boxPattern = boxPattern;
        this.boxQty = boxQty;
        this.quantity = quantity;
        this.supplierPrice = supplierPrice;
        this.totalPurchasePrice = totalPurchasePrice;
    }

    // Constructor excluding totalPurchasePrice (for inserting to database, will be calculated)
    public Purchase(int supplierId, String batchId, Date expiryDate, BigDecimal stockQty, String boxPattern, int boxQty, int quantity, BigDecimal supplierPrice) {
        this.supplierId = supplierId;
        this.batchId = batchId;
        this.expiryDate = expiryDate;
        this.stockQty = stockQty;
        this.boxPattern = boxPattern;
        this.boxQty = boxQty;
        this.quantity = quantity;
        this.supplierPrice = supplierPrice;
        calculateTotalPurchasePrice();
    }

    public Purchase() {}

    public Purchase(int id, int supplierId, String batchId, Date expiryDate, BigDecimal stockQty, String boxPattern, int boxQty, int quantity, BigDecimal supplierPrice) {
        this.supplierId = supplierId;
        this.batchId = batchId;
        this.expiryDate = expiryDate;
        this.stockQty = stockQty;
        this.boxPattern = boxPattern;
        this.boxQty = boxQty;
        this.quantity = quantity;
        this.supplierPrice = supplierPrice;
    }

    public void calculateTotalPurchasePrice() {
        if (supplierPrice != null) {
            this.totalPurchasePrice = supplierPrice.multiply(BigDecimal.valueOf(quantity));
        } else {
            this.totalPurchasePrice = BigDecimal.ZERO;
        }
    }

    // Getters and Setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public String getBoxPattern() {
        return boxPattern;
    }

    public void setBoxPattern(String boxPattern) {
        this.boxPattern = boxPattern;
    }

    public int getBoxQty() {
        return boxQty;
    }

    public void setBoxQty(int boxQty) {
        this.boxQty = boxQty;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPurchasePrice();
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
        calculateTotalPurchasePrice();
    }

    public BigDecimal getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    // No setter for totalPurchasePrice because it's a calculated field
}
