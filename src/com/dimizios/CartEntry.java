package com.dimizios;


import java.io.Serializable;

public class CartEntry extends Product implements Serializable {


    private int quantity;

    public CartEntry(String productId, int quantity) {
        super(productId);
        this.quantity = quantity;

    }



    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartEntry ID: " + super.getProductId() + ", " +
                "quantity: " + quantity;
    }
}
