package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContacFormDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "Es necesaria una problematica en el formulario")
    private String problematica;

    @NotBlank(message = "Es obligatorio poner una descripcion de la situacion")
    private String descripcion;

    @NotBlank(message = "Como minimo debe haber una imagen cargada")
    private String imagen;

    public ContactForm toContactform(){
        return ContactForm.builder()
                .nombre(nombre)
                .apellido(apellido)
                .problematica(problematica)
                .descripcion(descripcion)
                .imagenes(new ArrayList<>())
                .build();
    }
}