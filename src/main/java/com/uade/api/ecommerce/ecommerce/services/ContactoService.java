package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.dto.ContactoDTO;
import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.models.ImagenContacto;
import com.uade.api.ecommerce.ecommerce.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Transactional
    public Contacto guardarContacto(ContactoDTO contactoDTO, List<MultipartFile> imagenes) throws IOException, URISyntaxException {
        Contacto contacto = contactoDTO.toContacto();
        contacto = contactoRepository.save(contacto); // Guarda el contacto para obtener el ID

        for (int i = 0; i < imagenes.size(); i++) {
            MultipartFile imagen = imagenes.get(i);

            // Genera un nombre de archivo único
            String nombreArchivo = "imagen" + contacto.getId() + "_" + i + ".jpg";

            // Obtengo la ruta base del sistema para luego guardar la imagen en la ruta del proyecto mismo
            String rutaBase = System.getProperty("user.dir");
            Path rutaDestino = Paths.get(rutaBase + "/src/main/resources/img/" + nombreArchivo);

            // Guarda la imagen en el servidor
            imagen.transferTo(rutaDestino.toFile());

            // Crea una nueva ImagenContacto con la ruta de la imagen
            ImagenContacto imagenContacto = ImagenContacto.builder()
                    .rutaImagen("/src/main/resources/img/" + nombreArchivo)
                    .contacto(contacto)
                    .build();

            // Agrega la imagen a la lista de imágenes del contacto
            contacto.getImagenes().add(imagenContacto);
        }

        return contactoRepository.save(contacto); // Guarda el contacto con las imágenes
    }

}