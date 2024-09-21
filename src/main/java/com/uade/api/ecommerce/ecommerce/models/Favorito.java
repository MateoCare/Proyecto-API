package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@IdClass(ProductoUsuarioId.class)
public class Favorito {
    @Id
    private long usuarioId;

    @Id
    private long productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId", insertable = false, updatable = false)
    private Usuario usuariosAsociado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId", insertable = false, updatable = false)
    private Producto productoAsociado;

    public Favorito(long usuarioId, long productoId) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
    }
}

