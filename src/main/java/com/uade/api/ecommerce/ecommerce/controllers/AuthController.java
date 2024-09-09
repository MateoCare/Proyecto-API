package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.LoginDTO;
import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService personaService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDto){
        Usuario p = loginDto.toPersona();

        try{
            String token = personaService.login(p.getUsuario(), p.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/registro")
    public ResponseEntity guardarPersona(@RequestBody UsuarioDTO user) {
        Usuario usuario = user.toUsuario();
        Usuario nuevaPersona = personaService.registrarPersona(usuario);
        return ResponseEntity.ok(nuevaPersona);
    }




}
