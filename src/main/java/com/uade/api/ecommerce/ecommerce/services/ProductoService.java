package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.models.Favorito;
import com.uade.api.ecommerce.ecommerce.models.FavoritoId;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.FavoritoRepository;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockService stockService;
    @Autowired
    private StockProductoRepository stockProductoRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private Environment env;

    public List<Producto> findAll() {
        return productoRepository.findByStatusTrue();
    }

    public List<Producto> buscarProductosPorCategoria(List<Long> categorias) {
        return productoRepository.findByCategoriaFiltro(categorias, categorias.size());
    }

    public Producto addProducto(Producto producto) {
        producto.setStatus(true);
        return productoRepository.save(producto);
    }

    public Producto altaProducto(Producto producto) throws Exception {
        if (producto.isStatus()) {
            throw new Exception("El producto ya se encuentra dado de alta");
        }
        producto.setStatus(true);
        return productoRepository.save(producto);
    }

    public Producto addStock(StockDTO stockDTO, Producto producto) throws Exception{
        if (!producto.isStatus()) {
            throw new Exception("El producto se encuentra dado de baja");
        }
        stockService.addStock(stockDTO);
        return productoRepository.save(producto);
    }

    public void deleteStock(ProductoDTO productoDTO)
    {
        var stock = stockProductoRepository.findById(productoDTO.getId()).get();
        stockProductoRepository.delete(stock);
    }

    public void setUnsetFav(Usuario usuario, Long productoId) {
        Favorito favorito = new Favorito(usuario.getId(), productoId);

        Optional<Favorito> optFav = favoritoRepository.findById(new FavoritoId(usuario.getId(), productoId));
        if (optFav.isPresent()) {
            favoritoRepository.delete(favorito);
        } else {
            favoritoRepository.save(favorito);
        }
    }

    public List<Producto> buscarProductosDestacados() {
        int top = env.getProperty("consulta.destacados.top", Integer.class, 10);
        return productoRepository.findProductosDestacados(top);
    }

    public List<Producto> buscarVistosRecientemente(Usuario usuario) {

        return productoRepository.findProductosVistosRecientemente(usuario);
    }

    // TODO public producto delete
}
