package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity @Data

public class PersonaLogIn {

    @Id
    private String usuario;

    private String password;
}
