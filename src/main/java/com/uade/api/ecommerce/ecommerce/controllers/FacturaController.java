package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.services.FacturaService;
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
    public ResponseEntity crearFactura(@RequestBody CarritoDTO carritoDTO){
        try{
            var factura = facturaService.realizarCompra(carritoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(factura.toDTO());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //Mejorar el manejo de la excepcion
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity obtenerFactura(@PathVariable Long id) {
        System.out.println("trying to get factura");
        return ResponseEntity.ok(facturaService.obtenerFactura(id));
    }

    @GetMapping()
    public ResponseEntity obtenerTodasFacturas() {
        return ResponseEntity.ok(facturaService.obtenerFacturas());
    }
}
