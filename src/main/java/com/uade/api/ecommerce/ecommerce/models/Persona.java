package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data

public class Persona {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;


}
