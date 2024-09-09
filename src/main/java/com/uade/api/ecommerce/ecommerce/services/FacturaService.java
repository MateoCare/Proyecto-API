package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.models.ItemFactura;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.FacturaRepository;
import com.uade.api.ecommerce.ecommerce.repository.ItemFacturaRepository;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ItemFacturaRepository itemFacturaRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockProductoRepository stockProductoRepository;


    public Factura realizarCompra(CarritoDTO carritoDTO){
        var factura = Factura.builder();

        Usuario comprador = userRepository.findById(carritoDTO.getUsuarioId()).orElseGet(()->null);
        List<ItemFactura> listItemsFactura = carritoDTO.getListItems().stream()
                .map(itemDto -> {
                    var productoStock = stockProductoRepository.findById(itemDto.getStockProductoId()).orElse(null);

                    var itemFactura = ItemFactura.builder()
                            .stockProducto(productoStock)
                            .precioUnidad(productoStock.getProducto().getPrecio())
                            .unidad(itemDto.getCantidad())
                            .build();

                    return itemFactura;
                })
                .toList();

        itemFacturaRepository.saveAll(listItemsFactura);

        factura
                .fechaCompra(LocalDate.now())
                .comprador(comprador)
                .itemFacturas(listItemsFactura);

        return facturaRepository.save(factura.build());
    }
}
