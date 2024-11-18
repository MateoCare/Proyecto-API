package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.models.ImagenContacto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactoDTO
{
    private String nombreApellido;
    private String problematica;
    private String descripcion;
    private List<byte[]> imagenes;

    public Contacto toContacto() {
        return Contacto.builder()
                .nombreApellido(nombreApellido)
                .problematica(problematica)
                .descripcion(descripcion)
                .imagenes(imagenes.stream().map(imagen -> ImagenContacto.builder().imagen(imagen).build()).toList())
                .build();
    }
}