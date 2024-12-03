package com.uade.api.ecommerce.ecommerce.exceptions;

import com.uade.api.ecommerce.ecommerce.models.Categoria;

public class CheckoutException extends Exception{

    public CheckoutException() {
        super("error al efectuar checkout");
    }

    public CheckoutException(String message) {
        super(message);
    }
}
