package com.uade.api.ecommerce.ecommerce.exceptions;

public class MailAlreadyUsedException extends Exception {
    public MailAlreadyUsedException() {
        super("Ha ocurrido un error al intentar registrar el usuario");
    }
}
