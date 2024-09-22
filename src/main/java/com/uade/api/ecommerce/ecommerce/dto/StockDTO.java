package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import lombok.Data;

@Data
public class StockDTO {
    private Long id;
    private Producto producto;
    private Double talle;
    private int cantidad;

    public StockProducto toStock() {
        return StockProducto.builder()
                .talle(this.talle)
                .cantidad(this.cantidad)
                .build();
    }
}
