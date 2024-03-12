package com.restaurant.controller;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.dto.RestaurantNameDTO;
import com.restaurant.beans.dto.RestaurantPublisherInformationDTO;
import com.restaurant.service.RestaurantPublisherService;
import com.restaurant.service.serviceImpl.RestaurantPublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publisher")
public class RestaurantPublisherController {

    private RestaurantPublisherImpl restaurantPublisherService;


    @Autowired
    public RestaurantPublisherController(RestaurantPublisherImpl restaurantPublisherService){
        this.restaurantPublisherService=restaurantPublisherService;
    }
    @GetMapping("/add-publisher")
    public ResponseEntity<?> addRestaurantPublisher(){
        GlobalResponse<String> globalResponse = restaurantPublisherService.finalResponse();
        return ResponseEntity.ok(globalResponse);
    }

    @PostMapping("/get-publisher")
    public ResponseEntity<?> getRestaurantPublishers(@RequestBody RestaurantNameDTO restaurantNameDTO){
        GlobalResponse<RestaurantPublisherInformationDTO> restaurantPublishers = restaurantPublisherService.getRestaurantPublishers(restaurantNameDTO.getName());
        return ResponseEntity.ok(restaurantPublishers);
    }





}
