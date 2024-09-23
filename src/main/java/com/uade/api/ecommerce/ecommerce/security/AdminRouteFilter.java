package com.uade.api.ecommerce.ecommerce.security;

import com.uade.api.ecommerce.ecommerce.exceptions.ForbiddenRoute;
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
                this.handleException(response,new ForbiddenRoute());
                return;
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

    private void handleException(HttpServletResponse response, Exception ex) throws IOException {
        // Set the response status and content when an exception occurs
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");


        // Write the response body
        response.getOutputStream().print(ex.getMessage());
    }
}
