package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private List<CategoriaDTO> categorias;
    private List<StockDTO> stock;
    private boolean favorito = false;
    private boolean status = true;

    public Producto toProducto() {

        return Producto.builder()
                .nombre(this.nombre)
                .descripcion(this.descripcion)
                .imagen(this.imagen)
                .precio(this.precio)
                .stockProductos(this.stock != null ? this.stock.stream().map(StockDTO::toStock).toList() : null)
                .categoria(this.categorias != null ?
                        this.categorias.stream().map(CategoriaDTO::toCategoria).toList() : null)
                .status(this.status)
                .build();
    }
}
