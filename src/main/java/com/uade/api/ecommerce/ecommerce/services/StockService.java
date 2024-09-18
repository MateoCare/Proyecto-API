package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
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

//Del

    public StockProducto addStock(StockDTO stockDTO) {
        //Devuelve Stock del producto ORIGINAL que esta persistido en la BD
        StockProducto stockProducto = stockProductoRepository.findById(stockDTO.getId()).get();
        int suma = stockProducto.getCantidad() + stockDTO.getCantidad(); //suma cantidad persistente y la entrante
        stockProducto.setCantidad(suma); //setea el stock nuevo

        return stockProductoRepository.save(stockProducto);
    }

}
