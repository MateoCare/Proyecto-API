package com.uade.api.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContactFormDTO {

    @NotEmpty
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacio")
    private String apellido;

    @NotEmpty(message = "Es necesaria una problematica en el formulario")
    private String problematica;

    @NotEmpty(message = "Es obligatorio poner una descripcion de la situacion")
    private String descripcion;

    @NotEmpty(message = "Como minimo debe haber una imagen cargada")
    private List<byte[]> fotos;
}