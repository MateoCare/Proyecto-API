package com.uade.api.ecommerce.ecommerce.services;

import org.springframework.stereotype.Service;
import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.repository.ContactFormRepository;

@Service
public class ContactFormService {

    private final ContactFormRepository repository;

    public ContactFormService(ContactFormRepository repository) {
        this.repository = repository;
    }

    public ContactForm saveForm(ContactForm form) {
        return repository.save(form);
    }
}
