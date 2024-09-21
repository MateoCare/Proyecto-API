package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FavoritoId {
    private long usuarioId;
    private long productoId;
}
