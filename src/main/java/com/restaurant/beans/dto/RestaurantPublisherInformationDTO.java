package com.restaurant.beans.dto;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RestaurantPublisherInformationDTO implements Serializable {

    String address;
    String category;
    String name;
    String country;
    Timestamp dateAdded;
    Timestamp dateUpdated;
    String postalCode;
    String primaryCategory;
    String sourceUrl;
    String publisherId;
}
