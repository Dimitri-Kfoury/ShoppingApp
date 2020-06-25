package com.dimizzi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Product implements Comparable {

    private int productId;
    private Set<String> discreetFeatures;
    private HashMap<String, Double> numericalFeatures;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Product) {

            if (this.productId > ((Product) o).productId)
                return 1;
            else if (this.productId == ((Product) o).productId)
                return 0;

        }

        return -1;
    }

    private double cosineDistance(Product product) {

        double dotProduct = 0;
        double lengthA = this.getDiscreetFeatures().size();       // length of the first product to be compared
        double lengthB = product.getDiscreetFeatures().size();       // length of the second product to be compared

        for (String discreetFeature : this.getDiscreetFeatures()) {

            if (product.getDiscreetFeatures().contains(discreetFeature))
                dotProduct++;

        }

        for (Map.Entry<String, Double> numericalFeature : this.numericalFeatures.entrySet()) {

            lengthA += numericalFeature.getValue() * numericalFeature.getValue();
            if (product.getNumericalFeatures().containsKey(numericalFeature.getKey()))
                dotProduct += numericalFeature.getValue() * product.getNumericalFeatures().get(numericalFeature.getKey());


        }
        for (Map.Entry<String, Double> numericalFeature : product.numericalFeatures.entrySet()) {
            lengthB += numericalFeature.getValue() * numericalFeature.getValue();

        }

        lengthA = Math.sqrt(lengthA);
        lengthB = Math.sqrt(lengthB);

        return dotProduct / (lengthA * lengthB);


    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Set<String> getDiscreetFeatures() {
        return discreetFeatures;
    }

    public void setDiscreetFeatures(Set<String> discreetFeatures) {
        this.discreetFeatures = discreetFeatures;
    }

    public HashMap<String, Double> getNumericalFeatures() {
        return numericalFeatures;
    }

    public void setNumericalFeatures(HashMap<String, Double> numericalFeatures) {
        this.numericalFeatures = numericalFeatures;
    }
}
