package com.restaurant.service.serviceImpl;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.entities.RestaurantType;
import com.restaurant.repository.RestaurantDetailsRepo;
import com.restaurant.repository.RestaurantTypeReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantTypeImpl implements RestaurantTypeService {

    RestaurantDetailsRepo restaurantDetailsRepo;

    RestaurantDetailsImpl restaurantDetails;

    RestaurantTypeReposiory restaurantTypeReposiory;

    @Autowired
    public RestaurantTypeImpl(RestaurantDetailsRepo restaurantDetailsRepo,
                              RestaurantDetailsImpl restaurantDetails,
                              RestaurantTypeReposiory restaurantTypeReposiory){
        this.restaurantDetailsRepo=restaurantDetailsRepo;
        this.restaurantDetails=restaurantDetails;
        this.restaurantTypeReposiory=restaurantTypeReposiory;

    }
    @Override
    public void saveRestaurantName() {
        GlobalResponse<List<String>> restaurantName = restaurantDetails.getRestaurantName();
        List<String> data = restaurantName.getData();
        List<RestaurantType> restaurantTypes = new ArrayList<>();
        for(String e : data){
            RestaurantType restaurantType = new RestaurantType();
            restaurantType.setRestName(e);
            restaurantTypes.add(restaurantType);
        }
        restaurantTypeReposiory.saveAll(restaurantTypes);

    }
}
