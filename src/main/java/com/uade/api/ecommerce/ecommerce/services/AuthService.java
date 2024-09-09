package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private UserRepository personaRepository;

    public Usuario registrarPersona(Usuario persona){
        return personaRepository.save(persona);
    }

    public String login(String usuario, String password) throws Exception{
        Usuario u = personaRepository.findByUsuario(usuario);

        if (u != null) {
            if(u.getPassword().equals(password)){
                // TODO generar el token
                return "TODO Token Imp";
            }
            else{
                throw  new Exception("Login Incorrecto");
            }
        }

        throw new Exception("Usuario not found");
    }

}
