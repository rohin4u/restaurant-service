package com.restaurant.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.restaurant.beans.entities.RestaurantDetails;
import com.restaurant.beans.entities.RestaurantPublishers;
import org.hibernate.dialect.SimpleDatabaseVersion;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RestaurantPublishersCSVToJavaConverter {
    public static List<RestaurantPublishers> convertCsvToObjects(String csvFilePath) throws IOException, CsvException {

        Resource resource = new ClassPathResource("Datafiniti_Fast_Food_Restaurants_Jun19.csv");

        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> rows = reader.readAll();

            // Assuming the first row contains column headers
            String[] headers = rows.get(0);

            // Create a list to store Person objects
            List<RestaurantPublishers> restaurantDetailsList = new ArrayList<>();

            // Iterate through rows (starting from the second row)
            for (int i = 1; i < rows.size(); i++) {
                String[] data = rows.get(i);

                // Create a new Person object and set its properties
                RestaurantPublishers restaurantDetails = new RestaurantPublishers();
                for (int j = 0; j < headers.length; j++) {
                    switch (headers[j]) {
                        case "id":
                            restaurantDetails.setPublisherId(data[j]);
                            break;
                        case "dateAdded":
                            String input = data[j];
                            try{
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                Date date = simpleDateFormat.parse(input);
                                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                                String formattedDate = outputFormat.format(date);
                                restaurantDetails.setDateAdded(Timestamp.valueOf(formattedDate));

                            }catch (Exception e){

                            }

                            break;
                        case "dateUpdated":
                            String input1 = data[j];
                            try{
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                Date date = simpleDateFormat.parse(input1);
                                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                                String formattedDate = outputFormat.format(date);
                                restaurantDetails.setDateUpdated(Timestamp.valueOf(formattedDate));

                            }catch (Exception e){

                            }
                            break;
                        case "address":
                            restaurantDetails.setAddress(data[j]);
                            break;
                        case "categories":
                            restaurantDetails.setCategory(data[j]);
                            break;
                        case "primaryCategories":
                            restaurantDetails.setPrimaryCategory(data[j]);
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
                            restaurantDetails.setPostalCode(data[j]);
                            break;
                        case "province":
                            restaurantDetails.setProvince(data[j]);
                            break;
                        case "sourceURLs":
                            restaurantDetails.setSourceUrl(data[j]);
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


