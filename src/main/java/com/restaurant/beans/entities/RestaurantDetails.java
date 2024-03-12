package com.restaurant.beans.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "restaurant-details")
public class RestaurantDetails {

    @Column(name="rest-id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID restId;

    @Column(name="rest-address")
    String address;

    @Column(name="rest-city")
    String city;

    @Column(name="rest-country")
    String country;

    @Column(name="rest-keys", columnDefinition = "TEXT")
    String keys;

    @Column(name="rest-latitude")
    Double latitude;

    @Column(name="rest-longitude")
    Double longitude;

    @Column(name="rest-name")
    String name;

    @Column(name="rest-pincode")
    String pincode;

    @Column(name="rest-province")
    String province;

    @Column(name="rest-website", columnDefinition = "TEXT")
    String website;

    @ManyToOne
    RestaurantType type;


}
