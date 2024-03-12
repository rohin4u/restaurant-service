package com.restaurant.service.serviceImpl;

import com.restaurant.beans.dto.ErrorResponse;
import com.restaurant.beans.dto.ErrorType;
import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.entities.RestaurantDetails;
import com.restaurant.beans.entities.RestaurantType;
import com.restaurant.beans.exception.DetailsNotFound;
import com.restaurant.config.CsvToJavaObjectConverter;
import com.restaurant.repository.RestaurantDetailsRepo;
import com.restaurant.repository.RestaurantTypeReposiory;
import com.restaurant.service.RestaurantDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantDetailsImpl implements RestaurantDetailsService {

    RestaurantDetailsRepo restaurantDetailsRepo;

    RestaurantTypeReposiory restaurantTypeReposiory;

    @Value("${restaurant.file.location}")
    String path;

    @Autowired
    public RestaurantDetailsImpl(RestaurantDetailsRepo restaurantDetailsRepo, RestaurantTypeReposiory restaurantTypeReposiory){
        this.restaurantDetailsRepo=restaurantDetailsRepo;
        this.restaurantTypeReposiory=restaurantTypeReposiory;
    }

    @Override
    public GlobalResponse<String> saveRestaurantDetails(List<RestaurantDetails> restaurantDetailsList) {
        GlobalResponse globalResponse;
        try {
            restaurantDetailsRepo.saveAll(restaurantDetailsList);
            globalResponse = new GlobalResponse();
            globalResponse.setErrorResponse(null);
            globalResponse.setData(null);
            globalResponse.setMessage("data is saved");
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorType(ErrorType.DETAILS_COULD_NOT_BE_SAVED);
            errorResponse.setMessage("details couldn't be saved");
           globalResponse = new GlobalResponse();
           globalResponse.setMessage(e.getMessage());
           globalResponse.setData(null);
           globalResponse.setErrorResponse(errorResponse);
        }

      return globalResponse;

    }
    public GlobalResponse<String> finalResponse(){
        GlobalResponse globalResponse;
        try {
            List<RestaurantDetails> restaurantDetails = CsvToJavaObjectConverter.convertCsvToObjects(path);

            globalResponse = saveRestaurantDetails(restaurantDetails);
        }catch(Exception e){
            throw new DetailsNotFound();
        }
    return globalResponse;

    }
    public GlobalResponse<List<String>> getRestaurantName(){
        List<String> restaurantName = restaurantDetailsRepo.getRestName();
        getRestaurantOnlyName(restaurantName.get(0));
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setMessage(null);
        globalResponse.setData(restaurantName);
        globalResponse.setErrorResponse(null);
        return globalResponse;
    }

    public GlobalResponse<String> mergeRestaurantType(String restName){
        GlobalResponse globalResponse;
        Optional<RestaurantType> restaurantType = restaurantTypeReposiory.mergeRestaurant(restName);
        List<RestaurantDetails> restaurantDetails = restaurantDetailsRepo.findByRestName(restName);
        if(restaurantType.isEmpty()|| restaurantDetails.isEmpty() ){
            throw new DetailsNotFound();
        }
        RestaurantType restaurantType1 = restaurantType.get();
        List<RestaurantDetails> saveRestaurantDetailsWithType = new ArrayList<>();
        for(RestaurantDetails e : restaurantDetails){
            e.setType(restaurantType1);
            saveRestaurantDetailsWithType.add(e);
        }
        restaurantDetailsRepo.saveAll(saveRestaurantDetailsWithType);
        globalResponse = new GlobalResponse();
        globalResponse.setMessage("data is saved");
        globalResponse.setData(null);
        globalResponse.setErrorResponse(null);
        return globalResponse;

    }

    public String getRestaurantOnlyName(String name){
     return name;
    }
}
