package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImagenContacto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private Contacto contacto;
}