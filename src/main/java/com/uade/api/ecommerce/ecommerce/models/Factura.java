package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Factura {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario comprador;

    @OneToMany
    private List<ProductoFactura> productoFacturas;

}
