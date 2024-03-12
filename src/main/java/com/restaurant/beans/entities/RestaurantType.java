package com.restaurant.beans.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="rest-type")
    UUID typeId;

    @Column(name="rest-name")
    String restName;

}
