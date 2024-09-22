package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.services.ProductoService;
import com.uade.api.ecommerce.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;


    @GetMapping("/{id}")
    public ResponseEntity obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProducto(id));
    }

    @PostMapping()
    public ResponseEntity agregarProducto(@RequestBody ProductoDTO productoDTO) {

        var producto = productoService.addProducto(productoDTO.toProducto());

        stockService.initializeStock(productoDTO.getTalles(), producto);
        //TODO

        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/{productoId}/stock")
    public ResponseEntity agregarStock(@RequestBody StockDTO stockDTO, @PathVariable Long productoId) throws Exception {
        var stock = stockDTO.toStock();
        var producto = productoService.obtenerProducto(productoId);

        stock.setProducto(producto);

        var response = productoService.addStock(stock);

        return ResponseEntity.ok(response);
    }



    /**@PutMapping("/aumentoCantProducto")
    public ResponseEntity aumentarCantStock(@RequestBody StockDTO stockDTO, Producto producto) throws Exception {
        productoService.addStock(stockDTO, producto);
    }**/
}
