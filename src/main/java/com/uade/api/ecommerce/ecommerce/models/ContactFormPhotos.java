package com.uade.api.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ContactFormPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters y Setters
    @Setter
    @Getter
    @Lob
    private byte[] data;

    @Setter
    @ManyToOne
    @JoinColumn(name = "contact_form_id")
    private ContactForm contactForm;

}

