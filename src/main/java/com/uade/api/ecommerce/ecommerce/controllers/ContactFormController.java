package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.services.ContactFormService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor //Con esto puedo generar constructores finales
public class ContactFormController {

    private final ContactFormService service;

    @PutMapping("/submitForm")
    public ResponseEntity<String> submitForm(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("problematica") String problematica,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "fotos", required = false) MultipartFile[] fotos) {

        List<byte[]> imagenes = new ArrayList<>();
        if (fotos != null) {
            for (MultipartFile foto : fotos) {
                try {
                    imagenes.add(foto.getBytes()); // Convierte la foto a un array de bytes
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la foto: " + e.getMessage());
                }
            }
        }

        ContactForm form = new ContactForm();
        form.setNombre(nombre);
        form.setApellido(apellido);
        form.setProblematica(problematica);
        form.setDescripcion(descripcion);
        form.setFoto(imagenes);

        service.saveForm(form);

        return ResponseEntity.ok("Formulario enviado con éxito");
    }

}
