package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CategoriaDTO;
import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.models.Categoria;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.services.CategoriaService;
import com.uade.api.ecommerce.ecommerce.services.ProductoService;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producto")
public class ListaProductosController {

    @Autowired private ProductoService productoService;

    @Autowired private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductosPorCategoria(@RequestParam(name = "categorias", required = false) List<Long> categorias) {
        List<Producto> productos;
        if (categorias == null) {
            productos = productoService.findAll();
        } else {
            productos = productoService.buscarProductosPorCategoria(categorias);
        }

        List<ProductoDTO> productosDTO = productos.stream().map(Producto::toProductoDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productosDTO);
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<ProductoDTO>> listarProductosDestacados() {
        List<Producto> productos = productoService.buscarProductosDestacados();

        List<ProductoDTO> productosDTO = productos.stream().map(Producto::toProductoDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productosDTO);
    }

    @GetMapping("/recientes")
    public ResponseEntity<List<ProductoDTO>> listarProductosVistosRecientes() {
        Usuario usuario = SecurityUtils.getCurrentUser();

        List<Producto> productos = productoService.buscarVistosRecientemente(usuario);

        List<ProductoDTO> productosDTO = productos.stream().map(Producto::toProductoDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productosDTO);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(Categoria::toCategoriaDTO).toList();

        return ResponseEntity.status(200).body(categoriasDTO);
    }

    @PostMapping("/{productoId}/favorito")
    public ResponseEntity<ProductoDTO> setUnsetFavorito(@PathVariable Long productoId) {
        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(403).body(null);
        }

        productoService.setUnsetFav(usuario, productoId);

        return ResponseEntity.status(200).body(null);
    }
}
