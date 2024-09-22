package com.uade.api.ecommerce.ecommerce.security;

import com.uade.api.ecommerce.ecommerce.models.Rol;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class AdminRouteFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(this.shouldFilter(request)){
            Usuario user = SecurityUtils.getCurrentUser();
            if(user.getRol() != Rol.ADMIN){
                throw new ServletException("User does not have permission to access this resource");
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldFilter(HttpServletRequest request) {
        var path = request.getServletPath();
        boolean shouldFilter = false;

//       if(request.getMethod().equals("POST")){
//           if(path.equals("/producto")){
//               shouldFilter = true;
//           }
//       }

        return shouldFilter;
    }
}
