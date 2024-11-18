package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactoRepository extends JpaRepository <Contacto, Long>
{
    @Query("SELECT c FROM Contacto c LEFT JOIN FETCH c.imagenes WHERE c.id = :id")
    Optional<Contacto> findByIdWithImagenes(@Param("id") Long id);
}
