package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CategoriaDTO;
import com.uade.api.ecommerce.ecommerce.dto.ErrorDTO;
import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.PaginaFueraDelLimiteException;
import com.uade.api.ecommerce.ecommerce.models.Categoria;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.services.CategoriaService;
import com.uade.api.ecommerce.ecommerce.services.ProductoService;
import com.uade.api.ecommerce.ecommerce.util.PaginationUtils;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producto")
public class ListaProductosController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listarProductosPorCategoria(@RequestParam int page,
                                                                         @RequestParam int rowsPerPage,
                                                                         @RequestParam(name = "categorias", required
                                                                                 = false) List<Long> categorias) {
        if (rowsPerPage == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("rowsPerPage debe ser mayor a 0"));
        }

        List<Producto> productos;
        if (categorias == null) {
            productos = productoService.findAll();
        } else {
            productos = productoService.buscarProductosPorCategoria(categorias);
        }

        try {
            productos = PaginationUtils.paginar(productos, page, rowsPerPage);
        } catch (PaginaFueraDelLimiteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

        List<ProductoDTO> productosDTO = productos.stream().map(Producto::toProductoDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productosDTO);
    }

    @GetMapping("/destacados")
    public ResponseEntity<?> listarProductosDestacados(@RequestParam int page,
                                                       @RequestParam int rowsPerPage) {
        if (rowsPerPage == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("rowsPerPage debe ser mayor a 0"));
        }

        List<Producto> productos = productoService.buscarProductosDestacados();

        try {
            productos = PaginationUtils.paginar(productos, page, rowsPerPage);
        } catch (PaginaFueraDelLimiteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

        List<ProductoDTO> productosDTO = productos.stream().map(Producto::toProductoDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productosDTO);
    }

    @GetMapping("/recientes")
    public ResponseEntity<?> listarProductosVistosRecientes(@RequestParam int page,
                                                            @RequestParam int rowsPerPage) {
        if (rowsPerPage == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("rowsPerPage debe ser mayor a 0"));
        }

        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        List<Producto> productos = productoService.buscarVistosRecientemente(usuario);

        try {
            productos = PaginationUtils.paginar(productos, page, rowsPerPage);
        } catch (PaginaFueraDelLimiteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

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
    public ResponseEntity<ProductoDTO> setUnsetFavorito(@PathVariable(required = true) Long productoId) {
        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        productoService.setUnsetFav(usuario, productoId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/{productoId}/visto")
    public ResponseEntity<Void> marcarVisto(@PathVariable(required = true) long productoId) {
        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        productoService.marcarVisto(usuario, productoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
