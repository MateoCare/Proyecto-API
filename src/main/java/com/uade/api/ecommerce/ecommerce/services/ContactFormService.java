package com.uade.api.ecommerce.ecommerce.services;

import com.uade.api.ecommerce.ecommerce.models.ContactForm;
import com.uade.api.ecommerce.ecommerce.repository.ContactFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactFormService {

    private final ContactFormRepository repository;

    public void saveForm(ContactForm form) {
        repository.save(form);
    }

}
