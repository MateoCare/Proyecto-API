package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.repository.HistorialProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistorialProductosService {
    @Autowired
    private HistorialProductoRepository historialProductoRepository;

    @Transactional
    public int eliminarHistorialAntiguo(Date limitDate) {
        return historialProductoRepository.deleteByDateBefore(limitDate);
    }
}
