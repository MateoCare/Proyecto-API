package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarritoDTO {
    private Long usuarioId;
    private List<ItemCarritoDTO> listItems;
}


