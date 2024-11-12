package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.LoginDTO;
import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.CamposVaciosException;
import com.uade.api.ecommerce.ecommerce.exceptions.MailAlreadyUsedException;
import com.uade.api.ecommerce.ecommerce.models.Rol;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.security.JwtTokenUtil;
import com.uade.api.ecommerce.ecommerce.services.AuthService;
import com.uade.api.ecommerce.ecommerce.services.UserService;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import jakarta.validation.Valid;
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

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDto){
        Usuario p = loginDto.toPersona();

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);


        Usuario usu = SecurityUtils.getCurrentUser();

        String jwtToken = jwtTokenUtil.generateToken(loginDto.getEmail());
        if(usu.getRol() == Rol.ADMIN){
            return ResponseEntity.ok(Map.of("jwtToken", jwtToken, "admin", true));
        }

        return ResponseEntity.ok(Map.of("jwtToken", jwtToken));


    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> guardarPersona(@Valid @RequestBody UsuarioDTO user) throws CamposVaciosException, MailAlreadyUsedException {
        // Verifica si algún campo está vacío o contiene solo espacios en blanco
        if (user.getNombre().trim().isEmpty() || user.getApellido().trim().isEmpty() || user.getEmail().trim().isEmpty() ||
                user.getPassword().trim().isEmpty() || user.getUsuario().trim().isEmpty()) {
            throw new CamposVaciosException("No se pueden dejar espacios vacíos");
        }

        if (userService.buscarUsuarioPorMail(user.getEmail()).isPresent()) {
            throw new MailAlreadyUsedException();
        }

        Usuario usuario = user.toUsuario();
        Usuario nuevaPersona = authService.registrarPersona(usuario);

        UsuarioDTO usuarioDTO = nuevaPersona.toUsuarioDTO();
        return ResponseEntity.ok(usuarioDTO);
    }
}