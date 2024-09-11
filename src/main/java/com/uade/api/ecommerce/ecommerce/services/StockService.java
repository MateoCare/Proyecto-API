package com.uade.api.ecommerce.ecommerce.services;

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

    public StockProducto initializeStock(StockProducto stock){
        stock.setCantidad(0);
        stock.setTalle(0.0);
        return stock;
    }
}
