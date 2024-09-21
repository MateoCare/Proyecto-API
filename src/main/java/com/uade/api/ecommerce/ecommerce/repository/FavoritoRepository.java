package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.Favorito;
import com.uade.api.ecommerce.ecommerce.models.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    List<Favorito> findByUsuarioId(long usuarioId);
}
