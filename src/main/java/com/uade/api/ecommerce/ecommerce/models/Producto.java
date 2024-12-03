package com.uade.api.ecommerce.ecommerce.models;


import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column private String nombre;
    @Column private String descripcion;
    @Column private String imagen;
    @Column private Double precio;
    @Column private boolean status;

    @OneToMany(mappedBy = "producto")
    private List<StockProducto> stockProductos;

    @ManyToMany(fetch = FetchType.EAGER) private List<Categoria> categoria;

    public ProductoDTO toProductoDTO() {
        return ProductoDTO.builder()
                .id(id)
                .nombre(nombre)
                .descripcion(descripcion)
                .imagen(imagen)
                .precio(precio)
                .categorias(this.categoria != null ? this.categoria.stream().map(Categoria::toCategoriaDTO).toList() : null)
                .stock(this.stockProductos != null ? stockProductos.stream().map(StockProducto::toStockDTO).toList(): null)
                .status(status)
                .build();
    }
}
