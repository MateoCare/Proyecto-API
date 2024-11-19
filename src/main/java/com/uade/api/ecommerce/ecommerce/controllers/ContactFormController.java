package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.exceptions.ResourceNotFound;
import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.services.ContactFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import com.uade.api.ecommerce.ecommerce.models.ContactFormPhotos;

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
            @RequestParam(value = "fotos", required = false) MultipartFile[] fotos) {

        ContactForm form = new ContactForm();
        form.setNombre(nombre);
        form.setApellido(apellido);
        form.setProblematica(problematica);
        form.setDescripcion(descripcion);

        if (fotos != null) {
            for (MultipartFile foto : fotos)
                try {
                    ContactFormPhotos photo = new ContactFormPhotos();
                    photo.setData(foto.getBytes());
                    photo.setContactForm(form); // Relación bidireccional
                    form.getFotos().add(photo); // Añade la foto al formulario
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Error al procesar la foto: " + e.getMessage());
                }
        }

        service.saveForm(form);

        return ResponseEntity.ok("Formulario enviado con exito");
    }

    @GetMapping("/{id}/guardar-imagen")
    public ResponseEntity<String> guardarImagen(@PathVariable Long id) throws ResourceNotFound, IOException {
        ContactForm form = service.obtenerFormularioPorId(id);

        if (form.getFotos() == null || form.getFotos().isEmpty()) {
            return ResponseEntity.notFound().build(); // No hay imágenes asociadas al formulario
        }

        StringBuilder rutasGuardadas = new StringBuilder();
        int contador = 1;

        for (ContactFormPhotos foto : form.getFotos()) {
            byte[] fotoBytes = foto.getData();
            String rutaArchivo = "C:\\Users\\Mate\\Desktop\\Img_reports\\" + id + "_" + contador + ".jpg";
            service.guardarImagenEnArchivo(fotoBytes, rutaArchivo);
            rutasGuardadas.append(rutaArchivo).append("\n");
            contador++;
        }

        return ResponseEntity.ok("Imágenes guardadas con éxito:\n" + rutasGuardadas.toString());
    }
}