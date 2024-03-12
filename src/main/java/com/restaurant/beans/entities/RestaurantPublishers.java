package com.restaurant.beans.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="restaurant-publishers")
public class RestaurantPublishers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pub-rest-id")
    UUID pubRestId;

    @Column(name="publisher-id")
    String publisherId;

    @Column(name="date-added")
    Timestamp dateAdded;

    @Column(name="date-updated")
    Timestamp dateUpdated;

    @Column(name="address")
    String address;

    @Column(name="category", columnDefinition = "text")
    String category;

    @Column(name="primary-category")
    String primaryCategory;

    @Column(name="city")
    String city;

    @Column(name="country")
    String country;

    @Column(name="keys", columnDefinition = "text")
    String keys;

    @Column(name="latitude")
    Double latitude;

    @Column(name="longitude")
    Double longitude;

    @Column(name="name")
    String name;

    @Column(name="postal-code")
    String postalCode;

    @Column(name="province")
    String province;

    @Column(name="source-url", columnDefinition = "text")
    String sourceUrl;

    @Column(name="website", columnDefinition = "text")
    String website;

    @ManyToOne
    RestaurantType type;


}
