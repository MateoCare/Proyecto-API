package com.uade.api.ecommerce.ecommerce.exceptions;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(String.format("Error de validacion: %s", message));
    }}
