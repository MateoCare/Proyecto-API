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
    private StockProducto stockProducto;
    private int unidad;
    private Double precioUnidad;


    public ItemFacturaDTO toDTO(){
        ItemFacturaDTO itemFacturaDTO = ItemFacturaDTO.builder()
                .idProducto(stockProducto.getId())
                .precioUnidad(precioUnidad)
                .unidad(unidad)
                .build();
        return itemFacturaDTO;
    }
}
