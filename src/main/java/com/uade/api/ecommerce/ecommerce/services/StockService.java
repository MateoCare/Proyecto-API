package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockProductoRepository stockProductoRepository;

    public List<StockProducto> findAll(){ return stockProductoRepository.findAll(); }

    public void initializeStock(List<Double> talles, Producto producto) {

        for(Double talle : talles) {
            StockProducto stockProducto = new StockProducto();
            stockProducto.setProducto(producto);
            stockProducto.setCantidad(0);
            stockProducto.setTalle(talle);
            stockProductoRepository.save(stockProducto);
        }
    }
}
