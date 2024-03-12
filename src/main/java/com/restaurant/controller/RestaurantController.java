package com.restaurant.controller;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.dto.RestaurantNameDTO;
import com.restaurant.service.RestaurantDetailsService;
import com.restaurant.service.serviceImpl.RestaurantDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurant-controller")
public class RestaurantController {

    private RestaurantDetailsImpl restaurantDetailsService;

    @Autowired
    public RestaurantController(RestaurantDetailsImpl restaurantDetailsService){
        this.restaurantDetailsService=restaurantDetailsService;

    }

    @GetMapping("/add-restaurant")
    public ResponseEntity<?> addRestaurantDetails(){
        GlobalResponse<String> stringGlobalResponse = restaurantDetailsService.finalResponse();
        return ResponseEntity.ok(stringGlobalResponse);
    }

    @GetMapping("/restaurant-name")
    public ResponseEntity<?> getRestaurantName(){
        GlobalResponse<List<String>> restaurantName = restaurantDetailsService.getRestaurantName();
        return ResponseEntity.ok(restaurantName);

    }

    @PostMapping("/merge-restaurant")
    public ResponseEntity<?> saveRestaurantType(@RequestBody RestaurantNameDTO restaurantNameDTO){

        GlobalResponse<String> stringGlobalResponse = restaurantDetailsService.mergeRestaurantType(restaurantNameDTO.getName());
        return ResponseEntity.ok(stringGlobalResponse);
    }


}
