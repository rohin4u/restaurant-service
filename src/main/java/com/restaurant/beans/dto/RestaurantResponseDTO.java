package com.restaurant.beans.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantResponseDTO {
    String restaurantName;
    String restaurantAddress;
    String restaurantLink;
}
