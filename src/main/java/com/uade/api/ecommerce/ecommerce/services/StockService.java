package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Autowired
    private StockProductoRepository stockProductoRepository;

    public void initializeStock(List<Double> talles, Producto producto) {

        for(Double talle : talles) {
            StockProducto stockProducto = new StockProducto();
            stockProducto.setProducto(producto);
            stockProducto.setCantidad(0);
            stockProducto.setTalle(talle);
            stockProductoRepository.save(stockProducto);
        }
    }


    public StockProducto addStockProductoExistente(StockProducto stockAgregar) {
        //Devuelve Stock del producto ORIGINAL que esta persistido en la BD
        StockProducto stockProducto = stockProductoRepository.findById(stockAgregar.getId()).get();
        int suma = stockProducto.getCantidad() + stockAgregar.getCantidad(); //suma cantidad persistente y la entrante
        stockProducto.setCantidad(suma); //setea el stock nuevo

        return stockProductoRepository.save(stockProducto);
    }


    public StockProducto addStockNuevo(StockProducto stock){
        return stockProductoRepository.save(stock);
    }

    public void bajaProducto(Long stockId) throws Exception
    {
        var stock = this.obtenerStock(stockId);
        stockProductoRepository.delete(stock);
    }

    public StockProducto restoStock(Long id, int restaCantidad) throws Exception 
    {
        StockProducto stockProducto = stockProductoRepository.findById(id)
                .orElseThrow(() -> new Exception("StockProducto no encontrado"));
        int cantActual = stockProducto.getCantidad();
        if (cantActual < restaCantidad) {
            throw new Exception("No hay suficiente stock para restar");
        }
        stockProducto.setCantidad(cantActual - restaCantidad);
        return stockProductoRepository.save(stockProducto);
    }


    public List<StockProducto> batchActualizar(List<StockProducto> listadoActualizar){
        //En el caso que se quiera descontar muchos items, si algunos de los items a descontar tira error, esto hace un rollback
        //thank god for Hibernate 🙌
        return stockProductoRepository.saveAll(listadoActualizar);
    }


    public StockProducto obtenerStock(long id) throws ResourceNotFound {
        var result = stockProductoRepository.findById(id);
        if(result.isEmpty()){
            throw new ResourceNotFound(id);
        }

        return result.get();
    }
    

}
