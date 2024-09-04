package com.uade.api.ecommerce.ecommerce.models;

import com.uade.api.ecommerce.ecommerce.dto.LoginDTO;
import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity @Data
public class Usuario {
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

    public LoginDTO toLoginDto()
    {
        return new LoginDTO(usuario, password);
    }

    public UsuarioDTO toUsuarioDTO() {
        // TODO
        return new UsuarioDTO();
    }
}
