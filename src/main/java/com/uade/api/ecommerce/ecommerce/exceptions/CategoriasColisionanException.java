package com.uade.api.ecommerce.ecommerce.exceptions;

import com.uade.api.ecommerce.ecommerce.models.Categoria;

public class CategoriasColisionanException extends Exception {
    public CategoriasColisionanException(Categoria categoria) {
        super(String.format("No se puede asignar la categoria %s. El producto ya tiene asignada otra categoria para " +
                        "el grupo %s",
                categoria.getNombre(), categoria.getGrupo().getNombre()));
    }

    public CategoriasColisionanException(String message) {
        super(message);
    }
}
