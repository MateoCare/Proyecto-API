package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.models.Producto;
import com.uade.api.ecommerce.ecommerce.repository.ProductoRepository;
import com.uade.api.ecommerce.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping
public class StockController
{
    @Autowired
    private StockService stockService;

    @Autowired
    private ProductoRepository productoRepository;

   /** @DeleteMapping("/bajaStock")
    public void bajaProducto(@RequestBody ProductoDTO productoDTO){

    }

    @PutMapping("/aumentoStock")
    public void aumentoStock(@RequestBody ProductoDTO productoDTO){

    }**/

    @PostMapping("/restoStock")
    public void restoStock(@PathVariable Long id, @RequestBody int restaCantidad) throws Exception {
        stockService.restoStock(id, restaCantidad);
    }



}