package com.dimizios;


import java.io.Serializable;

public class DetailedProduct extends Product implements Serializable {

    private String description;
    private String productName;
    private byte[] imageBytes;

    public DetailedProduct(String productId, String productName, String description, byte[] imageBytes) {

        super(productId);
        this.productName = productName;
        this.description = description;
        this.imageBytes = imageBytes;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "id: " + super.getProductId() + " name: " + productName +
                " description: " + description;
    }
}
