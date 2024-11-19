package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.uade.api.ecommerce.ecommerce.models.ContactFormPhotos;

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
    @NotEmpty(message = "Es necesario un nombre")
    private String nombre;

    @Column(nullable = false)
    @NotEmpty(message = "Es necesario un apellido")
    private String apellido;

    @Column(nullable = false)
    @NotEmpty(message = "Es obligatorio indicar una problematica")
    private String problematica;

    @Column(nullable = false)
    @NotEmpty (message = "No puede faltar una descripcion del problema")
    private String descripcion;

    @CollectionTable(name = "contact_form_photos", joinColumns = @JoinColumn(name = "contact_form_id"))
    @Column(name = "foto_data", columnDefinition = "BLOB") //Esto va a definir las fotos como BLOBs
    @NotEmpty (message = "Como minimo debe ingresarse una foto")

    @OneToMany(mappedBy = "contactForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactFormPhotos> fotos = new ArrayList<>();
}
