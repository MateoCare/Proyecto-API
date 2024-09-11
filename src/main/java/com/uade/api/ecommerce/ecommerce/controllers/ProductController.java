package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.services.ProductoService;
import com.uade.api.ecommerce.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;

    @PostMapping("/producto")
    public ResponseEntity agregarProducto(@RequestBody ProductoDTO productoDTO, StockDTO stockDTO) {

        var producto = productoService.addProducto(productoDTO.toProducto());

        var stockInitialize = stockService.initializeStock(stockDTO.toStock());
        //TODO

        return ResponseEntity.ok().body(producto);
    }
}
