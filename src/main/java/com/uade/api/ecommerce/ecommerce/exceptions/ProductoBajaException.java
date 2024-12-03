package com.uade.api.ecommerce.ecommerce.exceptions;

public class ProductoBajaException extends Exception{
    public ProductoBajaException(){
        super("El producto se encuentra dado de baja");
    }
}
