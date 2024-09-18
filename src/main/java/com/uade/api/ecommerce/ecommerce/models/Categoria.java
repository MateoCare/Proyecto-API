package com.uade.api.ecommerce.ecommerce.models;

import com.uade.api.ecommerce.ecommerce.dto.CategoriaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @ManyToOne
    private GrupoCategoria grupo;

    public Categoria(long id) {
        this.id = id;
    }

    public CategoriaDTO toCategoriaDTO() {
        return CategoriaDTO.builder().id(id).nombre(nombre).idGrupo(grupo.getId()).nombreGrupo(grupo.getNombre()).build();
    }
}
