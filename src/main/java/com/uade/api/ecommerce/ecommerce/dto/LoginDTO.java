package com.uade.api.ecommerce.ecommerce.dto;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginDTO {
    private String usuario;
    private String password;

    public Usuario toPersona(){
        Usuario p = new Usuario();
        p.setUsuario(usuario);
        p.setPassword(password);

        return p;
    }
}
