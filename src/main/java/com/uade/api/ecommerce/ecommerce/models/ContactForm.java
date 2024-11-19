package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact_form")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String problematica;

    @Column(nullable = false)
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "contact_form_photos", joinColumns = @JoinColumn(name = "contact_form_id"))
    @Column(name = "foto_data", columnDefinition = "BLOB") //Esto va a definir las fotos como BLOBs
    private List<byte[]> fotos = new ArrayList<>(); // Lista de imágenes como bytes
}
