package com.uade.api.ecommerce.ecommerce.models;


import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
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

    @ManyToMany private List<Categoria> categoria;

    public ProductoDTO toProductoDTO() {
        return ProductoDTO.builder().id(id).nombre(nombre).descripcion(descripcion).imagen(imagen).precio(precio).build();
    }
}
