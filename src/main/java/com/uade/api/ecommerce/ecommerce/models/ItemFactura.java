package com.uade.api.ecommerce.ecommerce.models;


import com.uade.api.ecommerce.ecommerce.dto.ItemFacturaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class ItemFactura {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_producto_id")
    private StockProducto stockProducto;
    @Column
    private int unidad;
    @Column
    private Double precioUnidad;

    @ManyToOne
//    @JoinColumn(name = "factura_id")
    private Factura factura;


    public ItemFacturaDTO toDTO(){
        ItemFacturaDTO itemFacturaDTO = ItemFacturaDTO.builder()
                .idProducto(stockProducto.getId())
                .descripcion(String.format("%s Talle %.1f", stockProducto.getProducto().getNombre(), stockProducto.getTalle() ))
                .precioUnidad(precioUnidad)
                .unidad(unidad)
                .build();
        return itemFacturaDTO;
    }
}
