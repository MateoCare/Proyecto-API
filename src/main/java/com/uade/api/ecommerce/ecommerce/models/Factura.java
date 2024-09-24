package com.uade.api.ecommerce.ecommerce.models;

import com.uade.api.ecommerce.ecommerce.dto.FacturaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Factura {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario comprador;

    @OneToMany(mappedBy = "factura")
    private List<ItemFactura> itemFacturas;



    public FacturaDTO toDTO(){

        var totalFactura = 0D;

        for (ItemFactura itemFactura : itemFacturas) {
            totalFactura += itemFactura.getPrecioUnidad() * itemFactura.getUnidad();
        }

        FacturaDTO facturaDTO = FacturaDTO.builder()
                .nombreUsuario(String.format("%s %s", comprador.getNombre(), comprador.getApellido()))
                .items(itemFacturas.stream()
                        .map(ItemFactura::toDTO)
                        .toList())
                .total(totalFactura)
                .fechaCompra(fechaCompra)
                .build();

        return facturaDTO;
    }

}
