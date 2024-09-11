package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import lombok.Data;


@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;

    public Producto toProducto(){

        return Producto.builder()
                .nombre(this.nombre)
                .descripcion(this.descripcion)
                .imagen(this.imagen)
                .precio(this.precio)
                .build();
    }
}
