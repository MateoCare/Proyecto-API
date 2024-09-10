package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.LoginDTO;
import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.security.JwtTokenUtil;
import com.uade.api.ecommerce.ecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDto){
        Usuario p = loginDto.toPersona();

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        String jwtToken = jwtTokenUtil.generateToken(loginDto.getEmail());

        return ResponseEntity.ok(jwtToken);

    }

    @PostMapping("/registro")
    public ResponseEntity guardarPersona(@RequestBody UsuarioDTO user) {
        Usuario usuario = user.toUsuario();

        System.out.println(user);
        Usuario nuevaPersona = usuarioService.registrarPersona(usuario);
        return ResponseEntity.ok(nuevaPersona);
    }




}
