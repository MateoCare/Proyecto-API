package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.models.Persona;
import com.uade.api.ecommerce.ecommerce.models.dto.LoginDto;
import com.uade.api.ecommerce.ecommerce.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/API")

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/hello")
    public String hello(){
        return personaService.runServiceHola();
    }

    @GetMapping("/listaPersonas")
    public ResponseEntity listaPersonas(){
        List<Persona> obtenerLista = personaService.getListaPersonas();
        return ResponseEntity.ok(obtenerLista);
    }

    @PostMapping("/registro")
    public ResponseEntity guardarPersona(@RequestBody Persona persona){
        Persona nuevaPersona = personaService.registrarPersona(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){

        Persona p = this.loginDtoToPersona(loginDto);
        String personaLogin = personaService.login(p.getUsuario(), p.getPassword());
        return ResponseEntity.ok(personaLogin);
    }


    private Persona loginDtoToPersona(LoginDto loginDto){
        Persona p = new Persona();

        p.setUsuario(loginDto.getUsuario());
        p.setPassword(loginDto.getPassword());

        return p;
    }

}
