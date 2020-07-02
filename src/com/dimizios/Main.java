package com.dimizios;


import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("lel");
        list.add("lol");
        list.add("a");
        System.out.println(list);


    }


    public static void normalize(Double[] values) {

        double average = 0;
        for (Double value : values
        ) {
            average += value;


        }
        average = average / values.length;
        int i = 0;
        for (Double value : values
        ) {

            values[i] = value - average;
            i++;
        }

    }

    public static void printValues(Double[] values) {

        StringBuilder sb = new StringBuilder();
        for (Double value : values
        ) {
            sb.append(",");
            sb.append(value);
        }

        System.out.println(sb);
    }

}
