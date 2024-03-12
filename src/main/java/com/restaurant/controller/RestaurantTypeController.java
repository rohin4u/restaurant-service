package com.restaurant.controller;

import com.restaurant.service.serviceImpl.RestaurantTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add-type")
public class RestaurantTypeController {

    RestaurantTypeImpl restaurantType;

    @Autowired
    public RestaurantTypeController(RestaurantTypeImpl restaurantType){
        this.restaurantType=restaurantType;

    }


    @GetMapping("/save-type")
    public ResponseEntity<?> saveRestaurantType(){
        restaurantType.saveRestaurantName();
        return ResponseEntity.ok("saved");
    }
}
