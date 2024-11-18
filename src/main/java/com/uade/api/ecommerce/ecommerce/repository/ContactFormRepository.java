package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {
}
