/*
package com.dimizios;

*/
/**
 * Collaborative filtering is a class that implements tools that are used in recommending products using a utility matrix.
 *//*



import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;


import java.util.*;

public class CollaborativeFiltering {

    public static void main(String[] args) throws IOException, CsvValidationException {
        viewTsvFile("C:\\Users\\Dimitri\\Desktop\\amazon_reviews_us_Shoes_v1_00.tsv", 100);

    }


    private static void viewTsvFile(String path, int numberOfRows) throws IOException, CsvValidationException {


        CSVReader reader = new CSVReaderBuilder(new FileReader(path)).withCSVParser(new CSVParserBuilder().withSeparator('\t').build()).build();


        reader.skip(54100);
        String[] line1;
        line1 = reader.readNext();
        printSringValues(line1);

    }
        */
/**
         * Method that finds n similar users(or rows) to a target user(or row).
         *
         * @param n      is the number of similar users to find.
         * @param userId is the id of the target user.
         * @return a set of the n most similar neighbors
         *//*

        private static TreeSet<Neighbor> findSimilarUsers ( int n, int userId){

            TreeSet<Neighbor> neighborhood = new TreeSet<>();
            String[] fileLine;

            try {


                CSVReader reader = new CSVReader(new FileReader("Resources/Data.txt"));

                reader.skip(userId);

                double[] userRatings = normalizeRatings(reader.readNext()); // ratings of user for whom method finds similar users.

                reader.close();

                reader = new CSVReader(new FileReader("Resources/Data.txt"));

                reader.skip(1); // skipping header row

                double cosineDistance;

                while ((fileLine = reader.readNext()) != null) {

                    int neighborId = Integer.parseInt(fileLine[0]);

                    if (neighborId == userId)
                        continue;  // skip if reading original userId

                    double[] potentialNeighborRatings = normalizeRatings(fileLine);

                    cosineDistance = cosineDistance(userRatings, potentialNeighborRatings);

                    Neighbor potentialNeighbor = new Neighbor(neighborId, cosineDistance);


                    if (neighborhood.size() < n)
                        neighborhood.add(potentialNeighbor);

                    else {

                        Neighbor leastSimilarNeighbor = Collections.min(neighborhood);

                        if (potentialNeighbor.compareTo(leastSimilarNeighbor) > 0) {

                            neighborhood.remove(leastSimilarNeighbor);
                            neighborhood.add(potentialNeighbor);


                        }
                    }

                }


            } catch (IOException | CsvValidationException e) {

                e.printStackTrace();
            }

            return neighborhood;
        }

        */
/**
         * Returns an array of normalized ratings given an array of ratings(as Strings)
         *
         * @param Datarow is the array containing the ratings
         * @return an array of normalized ratings
         *//*

        private static double[] normalizeRatings (String[]Datarow){

            double[] normalizedRatings = new double[Datarow.length - 1]; // array holding normalized values of ratings as "double" values.
            double average = 0; // average rating for user across all items.
            int numberOfRatings = 0; // total number of rated products

            for (int i = 1; i < Datarow.length; i++) { // starting parsing from index 1 to omit userid.
                if (Datarow[i].equals(""))
                    normalizedRatings[i - 1] = -1; // representing blank entries as -1. (non normalized ratings cannot have negative values so -1 cannot be confused as a legitimate rating)
                else {
                    normalizedRatings[i - 1] = Double.parseDouble(Datarow[i]); // parsing "double" values of the ratings.
                    average += normalizedRatings[i - 1];
                    numberOfRatings++;
                }

            }

            average /= numberOfRatings;


            for (int i = 0; i < normalizedRatings.length; i++) {
                if (normalizedRatings[i] == -1)
                    normalizedRatings[i] = 0;
                else
                    normalizedRatings[i] -= average;

            }

            return normalizedRatings;

        }

        */
/**
         * CosineDistance calculates the cosine distance of two vectors (represented by arrays of double values).
         *
         * @param vector1 is the first vector.
         * @param vector2 is the second vector.
         * @return cosineDistance , the cosine distance between vector1 and vector2.
         *//*

        private static double cosineDistance ( double[] vector1, double[] vector2) throws IllegalArgumentException {

            double cosineDistance = 0; // cosine distance between vector1 and vector2
            double lenV1 = 0; // length or magnitude of vector 1.
            double lenV2 = 0; // length or magnitude of vector 2

            if (vector1.length != vector2.length) {
                throw new IllegalArgumentException();
            }

            for (int i = 0; i < vector1.length; i++) {

                cosineDistance += vector1[i] * vector2[i];
                lenV1 += vector1[i] * vector1[i];
                lenV2 += vector2[i] * vector2[i];
            }


            cosineDistance /= (Math.sqrt(lenV1) * Math.sqrt(lenV2));

            return cosineDistance;

        }

        */
/**
         * Class that represents a user or item as neighbor of another user or item.
         *//*

        private static class Neighbor implements Comparable {

            private int id; // id of user or item
            private double similarity; // similarity score

            */
/**
             * Constructor for the Neighbor class.
             *
             * @param id         is the id of the user or item
             * @param similarity is its similarity score
             *//*


            public Neighbor(int id, double similarity) {
                this.id = id;
                this.similarity = similarity;
            }

            */
/**
             * Natural ordering method of the neighbor class
             *//*

            @Override
            public int compareTo(Object o) {
                if (o instanceof Neighbor) {

                    if (((Neighbor) o).similarity > this.similarity)
                        return -1;
                    else if (((Neighbor) o).similarity < this.similarity)
                        return 1;
                    else {

                        return Integer.compare(this.id, ((Neighbor) o).id);

                    }
                }

                return -2;
            }

        }

        */
/**
         * Delete this method
         * Prints the contents of an array of doubles
         *//*

        public static void printValues ( double[] values){

            StringBuilder sb = new StringBuilder();
            for (double value : values
            ) {
                sb.append(",");
                sb.append(value);
            }

            System.out.println(sb);
        }

        */
/**
         * Delete this method
         * Prints the contents of an array of Strings
         *//*

        public static void printSringValues (String[]values){

            StringBuilder sb = new StringBuilder();
            for (String value : values
            ) {

                sb.append(value);
                sb.append(" ");
            }

            System.out.println(sb);
        }
    }
*/
