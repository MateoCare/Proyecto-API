package com.uade.api.ecommerce.ecommerce.util;

import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Usuario getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ((CustomUserDetails) authentication.getPrincipal()).getUsuario();
        }
        return null;
    }
}
