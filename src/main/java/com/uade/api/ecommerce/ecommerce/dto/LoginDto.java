package com.uade.api.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginDto {
    private String usuario;
    private String password;
}
