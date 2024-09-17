package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data @Builder
public class UsuarioDTO {
    private long id;
    private String usuario;
    private String password;
    private String email;
    private Date fecha_nacimiento;
    private String nombre;
    private String apellido;
    private int edad;

    public Usuario toUsuario() {

        return Usuario.builder()
                .usuario(this.usuario)
                .password(this.password)
                .email(this.email)
                .fechaNacimiento(this.fecha_nacimiento)
                .nombre(this.nombre)
                .apellido(this.apellido)
                .edad(this.edad)
                .build();
    }
}