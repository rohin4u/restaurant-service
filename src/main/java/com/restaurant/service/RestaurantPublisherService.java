package com.restaurant.service;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.entities.RestaurantPublishers;

import java.util.List;

public interface RestaurantPublisherService {

   GlobalResponse<String> addRestaurantPublisher(List<RestaurantPublishers> restaurantPublishersList);
}
