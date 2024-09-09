package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private Long stockProductoId;
    private int cantidad;
}