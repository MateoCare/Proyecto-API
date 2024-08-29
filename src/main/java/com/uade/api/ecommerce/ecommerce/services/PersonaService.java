package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Persona;
import com.uade.api.ecommerce.ecommerce.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public String runServiceHola(){
        return "Hola Mundo";
    }

    public Persona registrarPersona(@RequestBody Persona persona){
        return personaRepository.save(persona);
    }

    public List<Persona> getListaPersonas(){
        return personaRepository.findAll();
    }
}
