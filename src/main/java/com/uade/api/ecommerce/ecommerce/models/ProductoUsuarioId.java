package com.uade.api.ecommerce.ecommerce.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductoUsuarioId {
    private long usuarioId;
    private long productoId;
}
