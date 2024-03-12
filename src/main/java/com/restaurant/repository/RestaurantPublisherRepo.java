package com.restaurant.repository;

import com.restaurant.beans.projection.RestaurantPublishersDTO;
import com.restaurant.beans.entities.RestaurantPublishers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RestaurantPublisherRepo extends JpaRepository<RestaurantPublishers, UUID> {

    @Query(value="select \"restaurant-publishers\".\"address\" as address, \"restaurant-publishers\".category as category, \"restaurant-publishers\".country as country,\n" +
            "\"restaurant-publishers\".\"date-added\" as dateAdded, \"restaurant-publishers\".\"date-updated\" as dateUpdated, \"restaurant-publishers\".\"postal-code\" as postalCode,\n" +
            "\"restaurant-publishers\".\"primary-category\" as primaryCategory, \"restaurant-publishers\".\"source-url\" as sourceUrl, \"restaurant-publishers\".\"publisher-id\" as publisherId \n" +
            "from \"restaurant-publishers\" inner join \"restaurant_type\" on  \"restaurant-publishers\".\"name\"= \"restaurant_type\".\"rest-name\"  where \"restaurant-publishers\".\"name\" =:name", nativeQuery = true)
    List<RestaurantPublishersDTO> findPublisherByRestaurantName(String name);
}
