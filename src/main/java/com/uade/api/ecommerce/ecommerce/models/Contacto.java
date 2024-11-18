package com.uade.api.ecommerce.ecommerce.models;

import com.uade.api.ecommerce.ecommerce.dto.ContactoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contacto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreApellido;

    @Column(nullable = false)
    private String problematica;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "contacto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JoinColumn(name = "contacto_id")
    private List<ImagenContacto> imagenes;

    private ContactoDTO toDTO()
    {
        return ContactoDTO.builder()
                .nombreApellido(this.nombreApellido).descripcion(this.descripcion).problematica(this.problematica)
                .build();
    }
}