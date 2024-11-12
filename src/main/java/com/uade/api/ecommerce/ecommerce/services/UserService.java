package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public UsuarioDTO obtenerUsuario(Long id)
    {
        Usuario user = userRepository.findById(id).get();
        return user.toUsuarioDTO();
    }

    public Optional<Usuario> buscarUsuarioPorMail(String mail) {
        return userRepository.findByEmail(mail);
    }

}
