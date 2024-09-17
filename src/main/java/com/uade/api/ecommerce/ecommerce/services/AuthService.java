package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Rol;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository personaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarPersona(Usuario persona){
        persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        persona.setRol(Rol.USER);
        return personaRepository.save(persona);
    }
}
