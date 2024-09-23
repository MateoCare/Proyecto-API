package com.uade.api.ecommerce.ecommerce.security;

import com.uade.api.ecommerce.ecommerce.models.Rol;
import com.uade.api.ecommerce.ecommerce.models.Usuario;
import com.uade.api.ecommerce.ecommerce.util.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
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
        String[] endpointsPOST = {
                "/producto",
                "/producto/\\d+/stock",
                "/producto/\\d+/stock/\\d",
        };

        String[] endpointsDELETE = {
                "/producto/\\d",
                "/producto/\\d+/stock",
                "/producto/\\d+/stock/\\d",
        };

        boolean shouldFilter = false;

       if(request.getMethod().equals("POST")){
           shouldFilter = matchOverPatterns(endpointsPOST, path);
       }else if(request.getMethod().equals("DELETE")){
           shouldFilter = matchOverPatterns(endpointsDELETE, path);
       }

        return shouldFilter;
    }


    private boolean matchOverPatterns(String[] pattern, String compare){
        for(String endpoint: pattern){
            if(compare.matches(endpoint)){
                 return true;
            }
        }
        return false;
    }
}
