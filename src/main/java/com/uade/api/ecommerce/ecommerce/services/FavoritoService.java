package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Favorito;
import com.uade.api.ecommerce.ecommerce.models.ProductoUsuarioId;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    public boolean esFavorito(long producto, long usuario) {
        Optional<Favorito> favorito = favoritoRepository.findById(new ProductoUsuarioId(usuario, producto));
        return favorito.isPresent();
    }

    public List<Favorito> buscarFavoritos(Usuario usuario) {
        return favoritoRepository.findByUsuarioId(usuario.getId());
    }
}
