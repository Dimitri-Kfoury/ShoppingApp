package com.dimizios;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Product {

    private int productId;
    private String description;
    FileInputStream imageInputStream;

  /*  @Override
    public int compareTo(Object o) {
        if (o instanceof Product) {

            if (this.productId > ((Product) o).productId)
                return 1;
            else if (this.productId == ((Product) o).productId)
                return 0;
        }
        return -1;
    }*/

    public Product(){

    }

    public Product(int productId, String description) {
        this.productId = productId;
        this.description = description;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{}";
    }
}
