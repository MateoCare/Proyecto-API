package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import lombok.Builder;
import lombok.Data;

@Data@Builder
public class StockDTO {
    private Long id;
    private Double talle;
    private int cantidad;

    public StockProducto toStock() {
        return StockProducto.builder()
                .talle(this.talle)
                .cantidad(this.cantidad)
                .build();
    }
}
