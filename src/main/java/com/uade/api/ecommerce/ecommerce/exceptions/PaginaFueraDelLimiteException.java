package com.uade.api.ecommerce.ecommerce.exceptions;

import com.uade.api.ecommerce.ecommerce.dto.PageDTO;
import lombok.Getter;

public class PaginaFueraDelLimiteException extends Exception {
    @Getter
    private PageDTO<?> pageDTO;

    public PaginaFueraDelLimiteException(int page) {
        super(String.format("La pagina %s esta fuera del rango para la consulta indicada", page));
    }

    public PaginaFueraDelLimiteException(PageDTO<?> pageDTO) {
        super(String.format("La pagina %s esta fuera del rango para la consulta indicada", pageDTO.getCurrentPage()));
        this.pageDTO = pageDTO;
    }
}
