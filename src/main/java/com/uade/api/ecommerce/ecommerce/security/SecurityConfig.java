package com.uade.api.ecommerce.ecommerce.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain disableSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers("/h2-console/**", "/login", "/registrar/**", "/**")
                        .permitAll()
        ).csrf(csrf -> csrf.disable())
                .headers(head -> head.frameOptions(frame -> frame.disable()));

        return httpSecurity.build();
    }
}
