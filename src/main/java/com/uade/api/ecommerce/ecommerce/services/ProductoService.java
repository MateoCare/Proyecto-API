package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
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
    private StockProductoRepository stockProductoRepository;

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

// Baj

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
    //public producto delete
}
