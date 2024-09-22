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

        var producto = productoService.obtenerProducto(id).toProductoDTO();
        return ResponseEntity.ok(producto);
    }

    @PostMapping()
    public ResponseEntity agregarProducto(@RequestBody ProductoDTO productoDTO) {

        var producto = productoService.addProducto(productoDTO.toProducto());
        var listaTalles = productoDTO.getStock().stream().map(StockDTO::getTalle).toList();
        stockService.initializeStock(listaTalles, producto);
        //TODO

        return ResponseEntity.ok().body(producto.toProductoDTO());
    }

    @PostMapping("/{productoId}/stock")
    public ResponseEntity agregarStock(@RequestBody StockDTO stockDTO, @PathVariable Long productoId) throws Exception {
        var stock = stockDTO.toStock();
        var producto = productoService.obtenerProducto(productoId);

        stock.setProducto(producto);

        var response = productoService.addStock(stock);

        return ResponseEntity.ok(response.toProductoDTO());
    }

    @PostMapping("/{productoId}/stock/{stockId}")
    public ResponseEntity agregarCantidadStock(@RequestBody StockDTO stockDTO, @PathVariable Long productoId, @PathVariable Long stockId) throws Exception {
        var stock = stockDTO.toStock();
        stock.setId(stockId);
        var producto = productoService.obtenerProducto(productoId);
    
        stock.setProducto(producto);

        var response = productoService.addStockExistente(stock);

        return ResponseEntity.ok(response.toProductoDTO());
    }

}
