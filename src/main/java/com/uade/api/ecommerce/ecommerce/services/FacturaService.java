package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.CarritoDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.CheckoutException;
import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.models.ItemFactura;
import com.uade.api.ecommerce.ecommerce.models.StockProducto;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.FacturaRepository;
import com.uade.api.ecommerce.ecommerce.repository.ItemFacturaRepository;
import com.uade.api.ecommerce.ecommerce.repository.StockProductoRepository;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
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
    private StockService stockService;


    public Factura realizarCompra(CarritoDTO carritoDTO) throws CheckoutException
    {
        try{
            List<ItemFactura> listItemsFactura = carritoDTO.getListItems().stream()
                    .map(itemDto -> {
                        //remplazar con llamada a servicio correspondiente
                        StockProducto productoStock = null;
                        try {
                            productoStock = stockService.obtenerStock(itemDto.getStockProductoId());
                        } catch (ResourceNotFound e) {
                            throw new RuntimeException(e);
                        }

                        var itemFactura = ItemFactura.builder()
                                .stockProducto(productoStock)
                                .precioUnidad(productoStock.getProducto().getPrecio())
                                .unidad(itemDto.getCantidad())
                                .build();

                        return itemFactura;
                    })
                    .toList();

            List<StockProducto> listaStockProductos = listItemsFactura.stream().map(item -> {
                var stock = item.getStockProducto();
                stock.setCantidad(stock.getCantidad() - item.getUnidad());
                return stock;
            }).toList();
            stockService.batchActualizar(listaStockProductos);

            Usuario comprador = SecurityUtils.getCurrentUser();
            var factura = Factura.builder()
                    .comprador(comprador)
                    .fechaCompra(LocalDate.now());

            var facturaSaved = facturaRepository.save(factura.build());

            for (ItemFactura itemFactura : listItemsFactura)
            {
                itemFactura.setFactura(facturaSaved);
            }

            itemFacturaRepository.saveAll(listItemsFactura);

            facturaSaved.setItemFacturas(listItemsFactura);

            return facturaSaved;
        }catch (Exception e){
            throw new CheckoutException();
        }
    }

    public Factura obtenerFactura(Long id) throws ResourceNotFound
    {
        var result = facturaRepository.findById(id);
        if(result.isEmpty())
        {
            throw new ResourceNotFound(id);
        }
        return result.get();
    }

    public List<Factura> obtenerFacturas(Usuario comprador)
    {
        return facturaRepository.findByComprador(comprador);
    }
}
