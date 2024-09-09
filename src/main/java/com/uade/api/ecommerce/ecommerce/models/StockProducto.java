package com.uade.api.ecommerce.ecommerce.models;


import jakarta.persistence.*;

@Entity
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
