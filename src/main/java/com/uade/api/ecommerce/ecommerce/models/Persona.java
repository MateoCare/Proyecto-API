package com.uade.api.ecommerce.ecommerce.models;

import com.uade.api.ecommerce.ecommerce.dto.LoginDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity @Data

public class Persona {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date fecha_nacimiento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    public LoginDto toLoginDto()
    {
        return new LoginDto(usuario, password);
    }

}
