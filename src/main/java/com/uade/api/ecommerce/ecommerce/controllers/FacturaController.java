package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.FacturaDTO;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.models.ItemFactura;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;

import com.uade.api.ecommerce.ecommerce.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("v1/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;
    @Autowired
    private UserRepository userRepository; //Esto es una cochinada, retirar cuando tengamos el Auth y podamos sacar el Usu del contexto
    @Autowired
    private StockProductoRepository stockProductoRepository;

    @PostMapping()
    public ResponseEntity crearFactura(@RequestBody FacturaDTO factura) {

        var usu = userRepository.findById(factura.idUsuario);

        List<ItemFactura> listaItems = factura.Items.stream().map(item -> {

            StockProducto stockProducto = stockProductoRepository.findById(item.idProducto).get();

            System.out.println(stockProducto.getProducto());
            var itemFactura = ItemFactura.builder()
                    .unidad(item.unidad)
                    .stockProducto(stockProducto)
                    .precioUnidad(stockProducto.getProducto().getPrecio())
                    .build();

            return itemFactura;
        }).toList();

        Factura temp = Factura.builder()
                .comprador(usu.get())
                .itemFacturas(listaItems)
                .build();

        return ResponseEntity.ok(facturaService.realizarCompra(temp));
    }
}
