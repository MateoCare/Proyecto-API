package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.repository.FacturaRepository;
import com.uade.api.ecommerce.ecommerce.repository.ItemFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ItemFacturaRepository itemFacturaRepository;


    public Factura realizarCompra(Factura factura){

        factura.setItemFacturas(itemFacturaRepository.saveAll(factura.getItemFacturas()));
        return facturaRepository.save(factura);
    }
}
