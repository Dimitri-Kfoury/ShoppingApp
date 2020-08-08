package com.dimizios;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Product {


    public Product() {
    }

    private String productId;
    private String productName;


    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;

    }


    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
