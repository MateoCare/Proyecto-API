package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data@Builder
public class FacturaDTO {

    private String nombreUsuario;
    private List<ItemFacturaDTO> items;
    private Double total;
    private LocalDate fechaCompra;
}
