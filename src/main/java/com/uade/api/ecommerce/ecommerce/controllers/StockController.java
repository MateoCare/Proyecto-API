package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.dto.ProductoDTO;
import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import com.uade.api.ecommerce.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StockController
{
    @Autowired
    private StockService stockService;

   /** @DeleteMapping("/bajaStock")
    public void bajaProducto(@RequestBody ProductoDTO productoDTO){

    }

    @PutMapping("/aumentoStock")
    public void aumentoStock(@RequestBody ProductoDTO productoDTO){

    }**/
}