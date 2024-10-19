package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.dto.CategoriaDTO;
import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.Categoria;
import com.uade.api.ecommerce.ecommerce.services.ProductoService;
import com.uade.api.ecommerce.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) throws ResourceNotFound {

        var producto = productoService.obtenerProducto(id).toProductoDTO();
        return ResponseEntity.ok(producto);
    }

    @PostMapping()
    public ResponseEntity<ProductoDTO> agregarProducto(@RequestBody ProductoDTO productoDTO) {

        var producto = productoService.addProducto(productoDTO.toProducto());
//        var listaTalles = productoDTO.getStock().stream().map(StockDTO::getTalle).toList();

        return ResponseEntity.ok().body(producto.toProductoDTO());
    }

    @PostMapping("/{productoId}/stock")
    public ResponseEntity<ProductoDTO> agregarStock(@RequestBody StockDTO stockDTO, @PathVariable Long productoId) throws Exception {
        var stock = stockDTO.toStock();
        var producto = productoService.obtenerProducto(productoId);

        stock.setProducto(producto);

        var response = productoService.addStock(stock);

        return ResponseEntity.ok(response.toProductoDTO());
    }

    @PostMapping("/{productoId}/stock/{stockId}")
    public ResponseEntity<ProductoDTO> agregarCantidadStock(@RequestBody StockDTO stockDTO, @PathVariable Long productoId, @PathVariable Long stockId) throws Exception {
        var stock = stockDTO.toStock();
        stock.setId(stockId);
        var producto = productoService.obtenerProducto(productoId);

        stock.setProducto(producto);

        var response = productoService.addStockExistente(stock);

        return ResponseEntity.ok(response.toProductoDTO());
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoDTO productoDTO, @PathVariable Long productoId) throws Exception {
        var productoActualizado = productoDTO.toProducto();

        productoActualizado.setId(productoId);
        var response = productoService.actualizarProducto(productoActualizado);

        return ResponseEntity.ok(response.toProductoDTO());
    }


    @DeleteMapping("/{productoId}")
    public ResponseEntity<ProductoDTO> darBajaProducto(@PathVariable Long productoId) throws Exception {
        var response = productoService.bajaProducto(productoId);

        return ResponseEntity.ok(response.toProductoDTO());
    }


    @DeleteMapping("/{productoId}/stock/{stockId}")
    public ResponseEntity<Void> darBajaStock(@PathVariable Long productoId, @PathVariable Long stockId) throws Exception {
        productoService.eliminarStock(productoId, stockId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{idProducto}/alta")
    public ResponseEntity<ProductoDTO> altaProducto(@PathVariable Long idProducto) throws Exception {
        var response = productoService.altaProducto(idProducto);

        return ResponseEntity.ok(response.toProductoDTO());
    }

    @PostMapping("/{idProducto}/categoria")
    public ResponseEntity<ProductoDTO> asignarCategoria(@PathVariable Long idProducto, @RequestBody List<CategoriaDTO> categoriasDTO) throws ResourceNotFound {
        var producto = productoService.obtenerProducto(idProducto);

        var categorias = categoriasDTO.stream().map(CategoriaDTO::toCategoria).toList();

        productoService.asignarCategorias(producto, categorias);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idProducto}/categoria")
    public ResponseEntity<ProductoDTO> quitarCategoria(@PathVariable Long idProducto, @RequestBody List<CategoriaDTO> categoriasDTO) throws ResourceNotFound {
        var producto = productoService.obtenerProducto(idProducto);

        var categorias = categoriasDTO.stream().map(CategoriaDTO::toCategoria).toList();

        productoService.quitarCategorias(producto, categorias);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
