package com.uade.api.ecommerce.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class FacturaDTO {

    public Long idUsuario;
    public List<ItemFacturaDTO> Items;
}
