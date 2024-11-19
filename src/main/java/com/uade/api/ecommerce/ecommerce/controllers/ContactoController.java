package com.uade.api.ecommerce.ecommerce.controllers;

import com.uade.api.ecommerce.ecommerce.dto.ContactoDTO;
import com.uade.api.ecommerce.ecommerce.models.Contacto;
import com.uade.api.ecommerce.ecommerce.services.ContactoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true", methods = {RequestMethod.POST})
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @PostMapping(value = "/reporte", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> guardarContacto(@Valid @RequestPart("contactoDTO") ContactoDTO contactoDTO,
                                                  @RequestPart("imagenes") List<MultipartFile> imagenes) throws IOException, URISyntaxException {
        //Testeo
        //System.out.println("--------------------\nValor del parámetro imágenes: " + imagenes + "\n--------------------");
        //System.out.println("--------------------\nTamaño del parámetro imágenes: " + imagenes.size() + "\n--------------------");
        // imagenes.isEmpty() no me sirve ya que imagenes.size() siempre va a ser 1 por la instancia de MultipartFile que Spring agrega a imagenes
        if (imagenes.stream().allMatch(MultipartFile::isEmpty)) {
            return ResponseEntity.badRequest().body("Debe cargar al menos una imagen.");
        }
        Contacto contacto = contactoService.guardarContacto(contactoDTO, imagenes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Problema registrado correctamente.");
    }


}