package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    private String nombre;
    private String apellido;
    private String problematica;
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "contact_form_photos", joinColumns = @JoinColumn(name = "contact_form_id"))
    @Column(name = "foto_data", columnDefinition = "BLOB")
    private List<byte[]> foto; // Lista de imágenes como bytes
}
