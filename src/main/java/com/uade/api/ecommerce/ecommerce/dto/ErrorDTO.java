package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }
}
