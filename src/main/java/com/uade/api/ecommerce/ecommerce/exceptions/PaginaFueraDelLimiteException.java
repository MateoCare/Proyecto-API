package com.uade.api.ecommerce.ecommerce.exceptions;

public class PaginaFueraDelLimiteException extends Exception {
    public PaginaFueraDelLimiteException(int page) {
        super(String.format("La pagina %s esta fuera del rango para la consulta indicada", String.valueOf(page)));

    }
}
