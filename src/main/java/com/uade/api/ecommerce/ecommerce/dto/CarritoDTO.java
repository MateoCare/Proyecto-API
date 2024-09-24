package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarritoDTO {
    private List<ItemCarritoDTO> listItems;
}


