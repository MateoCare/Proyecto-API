package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.FacturaDTO;
import com.uade.api.ecommerce.ecommerce.dto.PageDTO;
import com.uade.api.ecommerce.ecommerce.dto.UsuarioDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.PaginaFueraDelLimiteException;
import com.uade.api.ecommerce.ecommerce.models.Factura;
import com.uade.api.ecommerce.ecommerce.services.FacturaService;
import com.uade.api.ecommerce.ecommerce.services.UserService;
import com.uade.api.ecommerce.ecommerce.util.PaginationUtils;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private FacturaService facturaService;

    @GetMapping()
    public ResponseEntity obtenerUsuario()
    {
        UsuarioDTO userDTO = userService.obtenerUsuario(SecurityUtils.getCurrentUser().getId());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/factura")
    public ResponseEntity<PageDTO<FacturaDTO>> obtenerTodasFacturas(@RequestParam int page,
                                               @RequestParam int rowsPerPage) throws PaginaFueraDelLimiteException {
        var response = facturaService.obtenerFacturas(SecurityUtils.getCurrentUser(), page, rowsPerPage);

        PageDTO<FacturaDTO> pageDTO = PaginationUtils.toPageDTO(response, Factura::toDTO);

        return ResponseEntity.ok(pageDTO);
    }
}
