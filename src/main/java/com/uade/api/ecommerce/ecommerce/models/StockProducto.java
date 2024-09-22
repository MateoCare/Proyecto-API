package com.uade.api.ecommerce.ecommerce.models;


import com.uade.api.ecommerce.ecommerce.dto.StockDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorValue;

@Entity@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class StockProducto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    @Column
    private Double talle;
    @Column
    @Min(value = 0) //Stock no puede tener numeros negativos
    private int cantidad;


    public StockDTO toStockDTO(){
        return StockDTO.builder()
                .id(id)
                .cantidad(cantidad)
                .talle(talle)
                .build();
    }
}
