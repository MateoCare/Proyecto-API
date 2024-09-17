package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.dto.FacturaDTO;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import com.uade.api.ecommerce.ecommerce.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;
    @Autowired
    private StockProductoRepository stockProductoRepository;

    @PostMapping()
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody CarritoDTO carritoDTO) {

        var factura = facturaService.realizarCompra(carritoDTO);

        return ResponseEntity.ok(factura.toDTO());
    }
}
