package com.dimizios;


public class CartEntry extends Product {
    private int quantity;

    public CartEntry(String productId,String productName, int quantity) {
        super(productId,productName);
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }
}
