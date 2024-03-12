package com.restaurant.controller;

import com.restaurant.beans.dto.GlobalResponse;
import com.restaurant.beans.entities.RestaurantDetails;
import com.restaurant.beans.entities.RestaurantType;
import com.restaurant.service.RestaurantDetailsService;
import com.restaurant.service.serviceImpl.RestaurantDetailsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
public class RestaurantServiceController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantDetailsImpl restaurantDetailsService;

    RestaurantDetails restaurantDetailsOne;

    RestaurantType restaurantTypeOne;


    List<RestaurantDetails> restaurantDetails = new ArrayList<>();

    List<String> restaurantNameList = new ArrayList<>();



    @BeforeEach
    void setUp(){
        restaurantTypeOne = new RestaurantType(UUID.fromString("6ded739e-5822-4895-8784-13e1a3708721"),
                "Baskin-Robbins");

        restaurantDetailsOne = new RestaurantDetails(UUID.fromString("da95d426-5c24-4fa1-abe0-062faf861b19"),
                "408 Market Square Dr",
                                "Maysville", "US", "us/ky/maysville/408marketsquaredr/1051460804",
                    38.6273600, -83.79141, "Frisch's Big Boy",
                "41056", "KY",
                "http://www.frischs.com,https://www.frischs.com/location/maysville-ky/",
                restaurantTypeOne);
        restaurantDetails.add(restaurantDetailsOne);
        restaurantNameList.add("Baskin-Robbins");
        restaurantNameList.add("OMG! Rotisserie");
    }
    @AfterEach
    void tearDown(){

    }

    @Test
    void getRestaurantDetails() throws Exception{
        when(restaurantDetailsService.getRestaurantOnlyName("Baskin-Robbins")).thenReturn("Baskin-Robbins");

    }

    @Test
    void getRestaurantName() throws Exception{
        GlobalResponse<List<String>> mockResponse = new GlobalResponse<>(null,restaurantNameList, null);
        when(restaurantDetailsService.getRestaurantName()).thenReturn(mockResponse);
        this.mockMvc.perform(get("/v1/restaurant-controller"+"/restaurant-name")).andDo(print()).andExpect(status().isOk());
    }

}
