package com.uade.api.ecommerce.ecommerce.models;


import jakarta.persistence.*;

@Entity
public class ProductoFactura {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StockProducto stockProducto;
    private int unidad;
    private Double precioUnidad;
}
