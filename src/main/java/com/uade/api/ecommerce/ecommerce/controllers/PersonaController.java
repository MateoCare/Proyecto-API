package com.uade.api.ecommerce.ecommerce.controllers;


import com.uade.api.ecommerce.ecommerce.models.Persona;
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

    @PostMapping("/guardar")
    public ResponseEntity guardarPersona(@RequestBody Persona persona){
        Persona nuevaPersona = personaService.guardarPersona(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

}
