package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.models.Producto;
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

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto addProducto(Producto producto) {
        producto.setStatus(true);
        return productoRepository.save(producto);
    }

// Baj

    public Producto altaProducto(Producto producto) throws Exception {
        if (producto.isStatus() == true) {
            throw new Exception("El producto ya se encuentra dado de alta");
        }
        producto.setStatus(true);
        return productoRepository.save(producto);
    }

    public Producto addStock(StockDTO stockDTO, Producto producto) throws Exception{
        if (producto.isStatus() == false) {
            throw new Exception("El producto se encuentra dado de baja");
        }
        stockService.addStock(stockDTO);
        return productoRepository.save(producto);
    }

    //Falta completar FACU
    //public producto delete
}
