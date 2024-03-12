package com.restaurant.service.serviceImpl;

import com.restaurant.beans.dto.*;

import com.restaurant.beans.entities.RestaurantPublishers;
import com.restaurant.beans.exception.DetailsNotFound;
import com.restaurant.beans.projection.RestaurantPublishersDTO;
import com.restaurant.config.RestaurantPublishersCSVToJavaConverter;
import com.restaurant.repository.RestaurantPublisherRepo;
import com.restaurant.service.RestaurantPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantPublisherImpl implements RestaurantPublisherService {

    RestaurantPublisherRepo restaurantPublisherRepo;

    @Value("${restaurant.file.location}")
    String path;

    @Autowired
    public RestaurantPublisherImpl(RestaurantPublisherRepo restaurantPublisherRepo){
       this.restaurantPublisherRepo=restaurantPublisherRepo;
    }


    public GlobalResponse<String> finalResponse(){
        System.out.println(path);
        GlobalResponse globalResponse;
        try {
            List<RestaurantPublishers> restaurantPublishers = RestaurantPublishersCSVToJavaConverter.convertCsvToObjects(path);

            globalResponse = addRestaurantPublisher(restaurantPublishers);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorType(ErrorType.DETAILS_COULD_NOT_BE_SAVED);
            errorResponse.setMessage("details couldn't be saved");
            globalResponse = new GlobalResponse();
            globalResponse.setMessage(e.getMessage());
            globalResponse.setData(null);
            globalResponse.setErrorResponse(errorResponse);
        }
        return globalResponse;

    }

    @Override
    public GlobalResponse<String> addRestaurantPublisher(List<RestaurantPublishers> restaurantPublishersList) {
        GlobalResponse globalResponse;
        try {
            restaurantPublisherRepo.saveAll(restaurantPublishersList);
            globalResponse = new GlobalResponse();
            globalResponse.setErrorResponse(null);
            globalResponse.setData(null);
            globalResponse.setMessage("data is saved");
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorType(ErrorType.DETAILS_COULD_NOT_BE_SAVED);
            errorResponse.setMessage("details couldn't be saved");
            globalResponse = new GlobalResponse();
            globalResponse.setMessage(e.getMessage());
            globalResponse.setData(null);
            globalResponse.setErrorResponse(errorResponse);
        }

        return globalResponse;
    }
    @Cacheable(value = "restaurant-pub", key = "#name+ ':' + #category")
    public GlobalResponse<RestaurantPublisherInformationDTO> getRestaurantPublishers(String name){
        System.out.println("got in fetch restaurant publisher");
        List<RestaurantPublishersDTO> publisherByRestaurantName = restaurantPublisherRepo.findPublisherByRestaurantName(name);
        if(publisherByRestaurantName.isEmpty()){
            throw new DetailsNotFound();
        }
        List<RestaurantPublisherInformationDTO> publisherList = new ArrayList<>();
        List<String> category = new ArrayList<>();
        for(RestaurantPublishersDTO e : publisherByRestaurantName){
            RestaurantPublisherInformationDTO publisherDTO = new RestaurantPublisherInformationDTO();
            publisherDTO.setAddress(e.getAddress());
            publisherDTO.setName(name);
            publisherDTO.setPublisherId(e.getPublisherId());
            publisherDTO.setCountry(e.getCountry());
            publisherDTO.setDateAdded(e.getDateAdded());
            publisherDTO.setDateUpdated(e.getDateUpdated());
            publisherDTO.setPostalCode(e.getPostalCode());
            publisherDTO.setPrimaryCategory(e.getPrimaryCategory());
            publisherDTO.setSourceUrl(e.getSourceUrl());
            publisherDTO.setCategory(e.getCategory());
            category.add(e.getCategory());
            publisherList.add(publisherDTO);
        }
        getCategory(category);


        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setErrorResponse(null);
        globalResponse.setData(publisherList);


        return globalResponse;

    }
    @Cacheable(value = "restaurant-publish", key = "#category")
    public List<String> getCategory(List<String> category){
        return category;
    }
}
