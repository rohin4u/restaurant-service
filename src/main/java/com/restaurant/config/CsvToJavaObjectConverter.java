package com.restaurant.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.restaurant.beans.entities.RestaurantDetails;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvToJavaObjectConverter {
    public static List<RestaurantDetails> convertCsvToObjects(String csvFilePath) throws IOException, CsvException {

        Resource resource = new ClassPathResource("FastFoodRestaurants.csv");

        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> rows = reader.readAll();

            // Assuming the first row contains column headers
            String[] headers = rows.get(0);

            // Create a list to store Person objects
            List<RestaurantDetails> restaurantDetailsList = new ArrayList<>();

            // Iterate through rows (starting from the second row)
            for (int i = 1; i < rows.size(); i++) {
                String[] data = rows.get(i);

                // Create a new Person object and set its properties
                RestaurantDetails restaurantDetails = new RestaurantDetails();
                for (int j = 0; j < headers.length; j++) {
                    switch (headers[j]) {
                        case "address":
                            restaurantDetails.setAddress(data[j]);
                            break;
                        case "city":
                            restaurantDetails.setCity(data[j]);
                            break;
                        case "country":
                            restaurantDetails.setCountry(data[j]);
                            break;
                        case "keys":
                            restaurantDetails.setKeys(data[j]);
                            break;
                        case "latitude":
                            restaurantDetails.setLatitude(Double.parseDouble(data[j]));
                            break;
                        case "longitude":
                            restaurantDetails.setLongitude(Double.parseDouble(data[j]));
                            break;
                        case "name":
                            restaurantDetails.setName(data[j]);
                            break;
                        case "postalCode":
                            restaurantDetails.setPincode(data[j]);
                            break;
                        case "province":
                            restaurantDetails.setProvince(data[j]);
                            break;
                        case "websites":
                            restaurantDetails.setWebsite(data[j]);
                            break;
                    }
                }

                // Add the Person object to the list
                restaurantDetailsList.add(restaurantDetails);
            }

            return restaurantDetailsList;
        }
    }
}

