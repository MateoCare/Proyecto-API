package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.services.FacturaService;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping()
    public ResponseEntity crearFactura(@RequestBody CarritoDTO carritoDTO) throws Exception {
        var factura = facturaService.realizarCompra(carritoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(factura.toDTO());
    }


    @GetMapping("/{id}")
    public ResponseEntity obtenerFactura(@PathVariable Long id) throws ResourceNotFound {
        var factura = facturaService.obtenerFactura(id);
        return ResponseEntity.ok(factura.toDTO());
    }

    @GetMapping()
    public ResponseEntity obtenerTodasFacturas() {
        return ResponseEntity.ok(facturaService.obtenerFacturas());
    }
}
