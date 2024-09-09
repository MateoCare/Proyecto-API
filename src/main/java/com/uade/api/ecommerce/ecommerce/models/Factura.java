package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Factura {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario comprador;

    @OneToMany
    private List<ItemFactura> itemFacturas;

}
