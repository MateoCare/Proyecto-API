package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    //BUSCA EL USUARIO
    Usuario findByUsuario(String usuario);

    Optional<Usuario> findByEmail(String email);


}
