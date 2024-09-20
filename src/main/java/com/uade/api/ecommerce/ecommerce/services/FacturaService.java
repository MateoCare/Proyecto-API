package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.models.ItemFactura;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
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

    @Autowired
    private StockService stockService;


    public Factura realizarCompra(CarritoDTO carritoDTO) throws Exception {
        var factura = Factura.builder();

        Usuario comprador = userRepository.findById(carritoDTO.getUsuarioId()).orElseGet(()->null);
        List<ItemFactura> listItemsFactura = carritoDTO.getListItems().stream()
                .map(itemDto -> {
                    //remplazar con llamada a servicio correspondiente
                    var productoStock = stockService.obtenerStock(itemDto.getStockProductoId());

                    var itemFactura = ItemFactura.builder()
                            .stockProducto(productoStock)
                            .precioUnidad(productoStock.getProducto().getPrecio())
                            .unidad(itemDto.getCantidad())
                            .build();

                    return itemFactura;
                })
                .toList();

//        //Esto funca por ahora, pero hay que preveer un rollback
//        for(var itemFactura: listItemsFactura){
//            stockService.restoStock(itemFactura.getStockProducto().getId(), itemFactura.getUnidad());
//        }

        List<StockProducto> listaStockProductos = listItemsFactura.stream().map(item -> {
            var stock = item.getStockProducto();
            stock.setCantidad(stock.getCantidad() - item.getUnidad());
            return stock;
        }).toList();

        stockService.batchActualizar(listaStockProductos);

        itemFacturaRepository.saveAll(listItemsFactura);

        factura
                .fechaCompra(LocalDate.now())
                .comprador(comprador)
                .itemFacturas(listItemsFactura);

        return facturaRepository.save(factura.build());
    }

    public Factura obtenerFactura(Long id){
        return facturaRepository.findById(id).orElse(null);
    }
}
