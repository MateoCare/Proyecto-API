package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String message;
    private Object extraInfo;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public ErrorDTO(String message, Object extraInfo) {
        this.extraInfo = extraInfo;
    }
}
