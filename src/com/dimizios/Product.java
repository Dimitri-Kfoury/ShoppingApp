package com.dimizios;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Product implements Serializable {


    public Product() {
    }

    private String productId;


    public Product(String productId) {
        this.productId = productId;

    }


    public String getProductId() {
        return productId;
    }

}
