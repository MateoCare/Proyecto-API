package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.models.ImagenContacto;
import com.uade.api.ecommerce.ecommerce.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    public Contacto guardarContacto(Contacto contacto) throws IOException {
        List<ImagenContacto> imagenes = contacto.getImagenes();
        for (int i = 0; i < imagenes.size(); i++) {
            ImagenContacto imagenContacto = imagenes.get(i);
            String rutaImagenOriginal = imagenContacto.getRutaImagen(); // Obtiene la ruta de la imagen en tu PC

            // Genera un nombre de archivo único
            String nombreArchivo = "imagen" + contacto.getId() + "_" + i + ".jpg"; // o el formato que desees
            Path rutaDestino = Paths.get("src/main/resources/img/" + nombreArchivo);

            // Copia la imagen de la ruta original a la ruta de destino
            Files.copy(Paths.get(rutaImagenOriginal), rutaDestino);

            // Guarda la ruta de la imagen en la base de datos
            imagenContacto.setRutaImagen("/img/" + nombreArchivo);
        }
        return contactoRepository.save(contacto);
    }
}