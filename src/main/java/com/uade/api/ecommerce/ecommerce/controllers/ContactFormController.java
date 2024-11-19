package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.services.ContactFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contact-forms")
@RequiredArgsConstructor //Con esto puedo generar constructores finales
public class ContactFormController {

    private final ContactFormService service;

    @PostMapping("/report")
    public ResponseEntity<String> submitForm(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("problematica") String problematica,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "fotos", required = false) MultipartFile[] fotos){

        List<byte[]> fotoBytes = new ArrayList<>();
        if (fotos != null) {
            for (MultipartFile foto : fotos)
                try {
                    fotoBytes.add(foto.getBytes());
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Error al procesar la foto: " + e.getMessage());
                }
        }

        ContactForm form = new ContactForm();
        form.setNombre(nombre);
        form.setApellido(apellido);
        form.setProblematica(problematica);
        form.setDescripcion(descripcion);
        form.setFotos(fotoBytes);

        service.saveForm(form);

        return ResponseEntity.ok("Formulario enviado con exito");
    }

}
