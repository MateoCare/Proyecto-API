package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.repository.ContactFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ContactFormService {

    private final ContactFormRepository repository;

    public void saveForm(ContactForm form) {
        repository.save(form);
    }

    public void guardarImagenEnArchivo(byte[] fotoBytes, String rutaArchivo) {
        try (FileOutputStream fos = new FileOutputStream(new File(rutaArchivo))) {
            fos.write(fotoBytes);
            System.out.println("Imagen guardada en: " + rutaArchivo);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen en el archivo: " + e.getMessage(), e);
        }
    }

    public ContactForm obtenerFormularioPorId(Long id) throws ResourceNotFound {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Formulario no encontrado con ID: " + id));
    }
}
