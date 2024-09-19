package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}/")
    public ResponseEntity obtenerUsuario(@RequestParam Long id)
    {
        Usuario user = userRepository.findById(id).get();
        return ResponseEntity.ok(user);
    }
}
