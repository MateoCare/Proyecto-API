package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.models.ImagenContacto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "El nombre y apellido es obligatorio.")
    private String nombreApellido;
    @NotBlank(message = "La problemática es obligatoria.")
    private String problematica;
    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;
    @NotEmpty(message = "Debe cargar al menos una imagen.")
    private List<String> rutasImagenes;

    public Contacto toContacto() {
        return Contacto.builder()
                .nombreApellido(nombreApellido)
                .problematica(problematica)
                .descripcion(descripcion)
                .imagenes(rutasImagenes.stream()
                        .map(ruta -> ImagenContacto.builder().rutaImagen(ruta).build())
                        .toList())
                .build();
    }
}