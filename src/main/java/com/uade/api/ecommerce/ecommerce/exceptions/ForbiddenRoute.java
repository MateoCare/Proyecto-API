package com.uade.api.ecommerce.ecommerce.exceptions;

import jakarta.servlet.ServletException;

public class ForbiddenRoute extends ServletException {

    public ForbiddenRoute() {
        super("Usted no tiene acceso a este recurso");
    }
}
