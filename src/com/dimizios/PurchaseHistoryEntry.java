package com.dimizios;

import java.io.Serializable;
import java.sql.Date;

public class PurchaseHistoryEntry extends DetailedProduct implements Serializable {

    private int quantity;
    private Date purchaseDate;


    public PurchaseHistoryEntry(String productId, String productName, String description, byte[] imageBytes, int quantity, Date purchaseDate) {
        super(productId, productName, description, imageBytes);
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
}
