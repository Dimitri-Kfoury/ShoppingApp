package com.dimizios;



public class CartEntry extends Product {


private int quantity;

    public CartEntry(String productId, int quantity) {
        super(productId);
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }
}
