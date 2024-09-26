package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.repository.FavoritoRepository;
import com.uade.api.ecommerce.ecommerce.repository.HistorialProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private HistorialProductoRepository historialProductoRepository;

    public List<Producto> findAll() {
        return productoRepository.findByStatusTrue();
    }

    public Producto obtenerProducto(Long id) throws ResourceNotFound {
        var found = productoRepository.findById(id);

        if(found.isEmpty()) {
            throw new ResourceNotFound(id);
        }

        return found.get();
    }

    public Producto addProducto(Producto producto) {
        producto.setStatus(true);
        var productoSaved = productoRepository.save(producto);

        var savedStock = stockService.initializeStock(productoSaved);
        producto.setStockProductos(savedStock);
        return producto;

    }

    public Producto altaProducto(Long productoId) throws Exception {
        var producto = obtenerProducto(productoId);

        if (producto.isStatus()) {
            throw new Exception("El producto ya se encuentra dado de alta");
        }
        producto.setStatus(true);
        return productoRepository.save(producto);
    }

    public Producto addStock(StockProducto stock) throws Exception{
        if (!stock.getProducto().isStatus()) {
            throw new Exception("El producto se encuentra dado de baja");
        }

        var resultStock = stockService.addStockNuevo(stock);
        return resultStock.getProducto();
    }

    public void eliminarStock(Long productoId, Long stockId) throws Exception {
        var producto = this.obtenerProducto(productoId);

        if(!producto.isStatus()){
            throw new Exception("El producto se encuentra dado de baja");
        }

        stockService.bajaProducto(stockId);

    }

    public Producto addStockExistente(StockProducto stock) throws Exception{
        if (!stock.getProducto().isStatus()) {
            throw new Exception("El producto se encuentra dado de baja");
        }

        var resultStock = stockService.addStockProductoExistente(stock);
        return resultStock.getProducto();
    }

    public Producto bajaProducto(Long productoId) throws ResourceNotFound {
        var actualizarProducto = this.obtenerProducto(productoId);

        actualizarProducto.setStatus(false);
        return productoRepository.save(actualizarProducto);
    }

    public Producto actualizarProducto(Producto producto) throws Exception {
        var productoFound = this.obtenerProducto(producto.getId());
        if (!productoFound.isStatus()) {
            throw new Exception("El producto se encuentra dado de baja");
        }
        productoFound.setNombre(producto.getNombre());
        productoFound.setDescripcion(producto.getDescripcion());
        productoFound.setPrecio(producto.getPrecio());
        productoFound.setImagen(producto.getImagen());
        return productoRepository.save(productoFound);
    }
}
