package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@IdClass(ProductoUsuarioId.class)
@Entity
public class HistorialProducto {
        @Id
        private long usuarioId;

        @Id
        private long productoId;

        @Column
        private Date date;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "usuarioId", insertable = false, updatable = false)
        private Usuario usuariosAsociado;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "productoId", insertable = false, updatable = false)
        private Producto productoAsociado;



        public HistorialProducto(long usuarioId, long productoId, Date date) {
            this.usuarioId = usuarioId;
            this.productoId = productoId;
            this.date = date;
        }
}
