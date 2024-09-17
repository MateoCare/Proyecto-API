package com.uade.api.ecommerce.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/producto")
public class ListaProductosController {

    @GetMapping
    public ResponseEntity listarProductos() {



        return ResponseEntity.ok(null);
    }

}
