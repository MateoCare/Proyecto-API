package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Categoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private long id;
    private String nombre;

    private long idGrupo;
    private String nombreGrupo;

    public Categoria toCategoria() {
        return new Categoria(id);
    }
}
