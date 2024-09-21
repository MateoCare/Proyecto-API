package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.HistorialProducto;
import com.uade.api.ecommerce.ecommerce.models.ProductoUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface HistorialProductoRepository extends JpaRepository<HistorialProducto, ProductoUsuarioId> {

    void findByUsuarioId(long usuarioId);

    int countByUsuarioId(long usuarioId);

    int deleteByDateBefore(Date date);
}
