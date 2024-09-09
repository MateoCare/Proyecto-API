package com.uade.api.ecommerce.ecommerce.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity@Data
public class StockProducto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Producto producto;
    @Column
    private Double talle;
    @Column
    private int cantidad;
}
