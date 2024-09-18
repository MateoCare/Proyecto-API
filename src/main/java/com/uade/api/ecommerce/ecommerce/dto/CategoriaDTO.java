package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private long id;
    private String nombre;

    private long idGrupo;
    private String nombreGrupo;
}
