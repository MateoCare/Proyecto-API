package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.FacturaDTO;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import com.uade.api.ecommerce.ecommerce.services.FacturaService;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/{id}")
    public ResponseEntity obtenerUsuario(@PathVariable Long id)
    {
        Usuario user = userRepository.findById(id).get();
        return ResponseEntity.ok(user.toUsuarioDTO());
    }

    @GetMapping("/factura")
    public ResponseEntity obtenerTodasFacturas() {

        var response = facturaService.obtenerFacturas(SecurityUtils.getCurrentUser());

        List<FacturaDTO> formated = response.stream().map(Factura::toDTO).toList();


        return ResponseEntity.ok(formated);
    }
}
