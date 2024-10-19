package com.uade.api.ecommerce.ecommerce.exceptions;

public class ResourceNotFound extends Exception{

    public ResourceNotFound(long idResource) {
        super(String.format("Resource %s not found", idResource));
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
