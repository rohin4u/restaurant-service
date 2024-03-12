package com.restaurant.beans.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    String message;
    ErrorType errorType;
    private LocalDateTime dateTime;
}
