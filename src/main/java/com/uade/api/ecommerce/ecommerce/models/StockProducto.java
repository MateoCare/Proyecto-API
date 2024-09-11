package com.uade.api.ecommerce.ecommerce.models;


import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class StockProducto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Producto producto;
    @Column
    private Double talle;
    @Column
    private int cantidad;


    public StockDTO toStockDTO(){
        return new StockDTO();
    }
}
