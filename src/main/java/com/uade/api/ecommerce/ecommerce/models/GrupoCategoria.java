package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GrupoCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;

    @Column
    private String nombre;

}
