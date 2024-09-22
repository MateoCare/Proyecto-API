package com.uade.api.ecommerce.ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private AdminRouteFilter adminRouteFilter;

    @Bean
    public SecurityFilterChain firstFilter(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );

//        httpSecurity.addFilterBefore(
//                adminRouteFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );

        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/h2-console/**", "/auth/login", "/auth/registro")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(crsf -> crsf.disable())
                .headers(head -> head.frameOptions(frame -> frame.disable()));

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
