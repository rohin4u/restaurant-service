package com.restaurant.beans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalResponse<T> implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ErrorResponse errorResponse;

    public GlobalResponse(boolean b, String success, List<String> restaurantNameList) {
    }
}
