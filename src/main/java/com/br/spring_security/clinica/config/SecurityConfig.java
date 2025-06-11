package com.br.spring_security.clinica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})                       // Habilita CORS
                .csrf(csrf -> csrf.disable())           // Desabilita CSRF
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()           // Libera todas as rotas sem autenticação
                )
                .formLogin(form -> form.disable())      // Desativa formulário de login
                .httpBasic(basic -> basic.disable());   // Desativa autenticação básica

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Ainda útil se você quiser salvar senhas codificadas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // Mantido para compatibilidade, mesmo não sendo usado
    }
}