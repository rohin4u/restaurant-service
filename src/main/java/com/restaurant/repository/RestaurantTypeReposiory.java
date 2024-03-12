package com.restaurant.repository;

import com.restaurant.beans.entities.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantTypeReposiory extends JpaRepository<RestaurantType, UUID> {

    @Query(value="select * from \"restaurant_type\" where \"rest-name\" =:restName", nativeQuery = true)
    Optional<RestaurantType> mergeRestaurant(String restName);
}
