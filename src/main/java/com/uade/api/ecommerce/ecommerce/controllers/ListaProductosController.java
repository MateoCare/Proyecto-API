package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.CategoriaDTO;
import com.uade.api.ecommerce.ecommerce.dto.ErrorDTO;
import com.uade.api.ecommerce.ecommerce.dto.PageDTO;
import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.PaginaFueraDelLimiteException;
import com.uade.api.ecommerce.ecommerce.exceptions.ValidationException;
import com.uade.api.ecommerce.ecommerce.models.*;
import com.uade.api.ecommerce.ecommerce.services.CategoriaService;
import com.uade.api.ecommerce.ecommerce.services.FavoritoService;
import com.uade.api.ecommerce.ecommerce.services.ListaProductosService;
import com.uade.api.ecommerce.ecommerce.util.PaginationUtils;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producto")
public class ListaProductosController {

    @Autowired
    private ListaProductosService listaProductoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping
    public ResponseEntity<?> listarProductosPorCategoria(@RequestParam int page,
                                                                         @RequestParam int rowsPerPage,
                                                                         @RequestParam(name = "categorias", required = false) List<Long> categorias) throws ValidationException, PaginaFueraDelLimiteException {
        if (rowsPerPage == 0) {
            throw new ValidationException("rowsPerPage debe ser mayor a 0");
        }

        Page<Producto> productos = null;
        Usuario user = SecurityUtils.getCurrentUser();
        boolean admin= user.getRol() == Rol.ADMIN;
        if (categorias == null) {
            productos = listaProductoService.buscarProductos(page, rowsPerPage, admin);
        } else {
            productos = listaProductoService.buscarProductosPorCategoria(categorias, page, rowsPerPage);
        }

        PageDTO<ProductoDTO> pageDTO = PaginationUtils.toPageDTO(productos, Producto::toProductoDTO);

        Usuario usuario = SecurityUtils.getCurrentUser();
        setearFavoritos(pageDTO.getPageItems(), usuario);

        return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
    }

    @GetMapping("/destacados")
    public ResponseEntity<?> listarProductosDestacados(@RequestParam int page,
                                                       @RequestParam int rowsPerPage) throws ValidationException, PaginaFueraDelLimiteException {
         if (rowsPerPage == 0) {
            throw new ValidationException("rowsPerPage debe ser mayor a 0");
        }

        Page<Producto> productos = listaProductoService.buscarProductosDestacados(page, rowsPerPage);

        PageDTO<ProductoDTO> pageDTO = PaginationUtils.toPageDTO(productos, Producto::toProductoDTO);

        Usuario usuario = SecurityUtils.getCurrentUser();
        setearFavoritos(pageDTO.getPageItems(), usuario);

        return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
    }

    @GetMapping("/recientes")
    public ResponseEntity<?> listarProductosVistosRecientes(@RequestParam int page,
                                                            @RequestParam int rowsPerPage) throws PaginaFueraDelLimiteException {
        if (rowsPerPage == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("rowsPerPage debe ser mayor a 0"));
        }

        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Page<Producto> productos = listaProductoService.buscarVistosRecientemente(usuario, page, rowsPerPage);

        PageDTO<ProductoDTO> pageDTO = PaginationUtils.toPageDTO(productos, Producto::toProductoDTO);

        setearFavoritos(pageDTO.getPageItems(), usuario);

        return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        listaProductoService.setUnsetFav(usuario, productoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{productoId}/visto")
    public ResponseEntity<Void> marcarVisto(@PathVariable(required = true) long productoId) {
        Usuario usuario = SecurityUtils.getCurrentUser();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        listaProductoService.marcarVisto(usuario, productoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favoritos")
    public ResponseEntity obtenerFavoritosUsuario(@RequestParam int page,
                                                   @RequestParam int rowsPerPage) throws PaginaFueraDelLimiteException {
        Usuario usu = SecurityUtils.getCurrentUser();

        if (usu == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Page<Producto> result = listaProductoService.obtenerFavoritosPorUsuario(page, rowsPerPage, usu);
        PageDTO<ProductoDTO> pageDTO = PaginationUtils.toPageDTO(result, Producto::toProductoDTO);

        return ResponseEntity.ok(pageDTO);
    }

    private void setearFavoritos(List<ProductoDTO> productosDTO, Usuario usuario) {
        List<Favorito> favoritos = favoritoService.buscarFavoritos(usuario);

        for (var productoDTO : productosDTO) {
            productoDTO.setFavorito(favoritos.stream().anyMatch(favorito -> favorito.getProductoId() == productoDTO.getId()));
        }
    }
}
