package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.ContactoDTO;
import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.services.ContactoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @PostMapping
    public ResponseEntity<String> guardarContacto(@Valid @RequestBody ContactoDTO contactoDTO) {
        Contacto contacto = contactoDTO.toContacto();
        contactoService.guardarContacto(contacto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Problema registrado correctamente.");
    }
}