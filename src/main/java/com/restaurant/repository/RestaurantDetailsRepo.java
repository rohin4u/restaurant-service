package com.restaurant.repository;

import com.restaurant.beans.entities.RestaurantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantDetailsRepo extends JpaRepository<RestaurantDetails, String> {

    @Query(value = "select distinct(\"rest-name\") from \"restaurant-details\"", nativeQuery = true)
    List<String> getRestName();


    @Query(value="select * from \"restaurant-details\" where \"rest-name\"= :restName", nativeQuery = true)
    List<RestaurantDetails> findByRestName(String restName);
}
