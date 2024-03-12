package com.restaurant.beans.projection;

import java.sql.Timestamp;

public interface RestaurantPublishersDTO {

    String getAddress();
    String getCategory();
    String getName();
    String getCountry();
    Timestamp getDateAdded();
    Timestamp getDateUpdated();
    String getPostalCode();
    String getPrimaryCategory();
    String getSourceUrl();
    String getPublisherId();


}
