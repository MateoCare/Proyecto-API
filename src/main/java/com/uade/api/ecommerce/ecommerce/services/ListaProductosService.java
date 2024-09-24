package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.*;
import com.uade.api.ecommerce.ecommerce.repository.FavoritoRepository;
import com.uade.api.ecommerce.ecommerce.repository.HistorialProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.ListaProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ListaProductosService {

    @Autowired
    private ListaProductosRepository listaProductosRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private HistorialProductoRepository historialProductoRepository;

    @Autowired
    private Environment env;

    public Page<Producto> buscarProductos(int page, int rowsPerPage) {
        return listaProductosRepository.findAll(PageRequest.of(page, rowsPerPage));
    }

    public Page<Producto> buscarProductosDestacados(int page, int rowsPerPage) {
        int top = env.getProperty("consulta.destacados.top", Integer.class, 10);
        return listaProductosRepository.findProductosDestacados(top, PageRequest.of(page, rowsPerPage));
    }

    public Page<Producto> buscarVistosRecientemente(Usuario usuario, int page, int rowsPerPage) {
        return listaProductosRepository.findProductosVistosRecientemente(usuario.getId(), PageRequest.of(page, rowsPerPage));
    }

    public void setUnsetFav(Usuario usuario, Long productoId) {
        Favorito favorito = new Favorito(usuario.getId(), productoId);

        Optional<Favorito> optFav = favoritoRepository.findById(new ProductoUsuarioId(usuario.getId(), productoId));
        if (optFav.isPresent()) {
            favoritoRepository.delete(favorito);
        } else {
            favoritoRepository.save(favorito);
        }
    }

    public void marcarVisto(Usuario usuario, long productoId) {
        historialProductoRepository.save(new HistorialProducto(usuario.getId(), productoId, new Date()));
    }

    public Page<Producto> buscarProductosPorCategoria(List<Long> categorias, int page, int rowsPerPage) {
        return listaProductosRepository.findByCategoriaFiltro(categorias, categorias.size(), PageRequest.of(page, rowsPerPage));
    }
}
