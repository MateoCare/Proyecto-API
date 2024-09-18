package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @ManyToOne
    private GrupoCategoria grupo;
}
