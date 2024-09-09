package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data@Builder
public class ItemFacturaDTO {

    private Long idProducto;
    private double precioUnidad;
    private int unidad;
}
