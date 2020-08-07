package com.dimizios;

import java.io.File;
import java.io.FileInputStream;

public class DetailedProduct extends Product {

    private String description;
    private FileInputStream imageStream;

    public DetailedProduct(String productId, String description, FileInputStream imageStream) {
        super(productId);
        this.description = description;
        this.imageStream = imageStream;
    }


    public String getDescription() {
        return description;
    }

    public FileInputStream getImageStream() {
        return imageStream;
    }
}
