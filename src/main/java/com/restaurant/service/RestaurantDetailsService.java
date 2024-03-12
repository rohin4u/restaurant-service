package com.restaurant.service;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.dto.RestaurantResponseDTO;
import com.restaurant.beans.entities.RestaurantDetails;

import java.util.List;

public interface RestaurantDetailsService {

    GlobalResponse<String> saveRestaurantDetails(List<RestaurantDetails> restaurantDetailsList);
}
