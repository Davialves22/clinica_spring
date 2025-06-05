// package com.br.spring_security.clinica.config;

// import com.br.spring_security.clinica.service.UsuarioService;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final UsuarioService service;

//     public SecurityConfig(UsuarioService service) {
//         this.service = service;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .cors()
//                 .and()
//                 .csrf().disable()
//                 .authorizeHttpRequests(authz -> authz
//                         //.requestMatchers("/", "/home", "/login", "/api/public/**").permitAll()
//                         //.requestMatchers("/usuarios/**").hasAuthority("ADMIN")
//                         //.requestMatchers("/api/medicos/**").hasAuthority("MEDICO")
//                         .anyRequest().permitAll() // Libera tudo
//                 )
//                 .formLogin(form -> form
//                         .loginProcessingUrl("/login")
//                         .successHandler((request, response, authentication) -> response.setStatus(200))
//                         .failureHandler((request, response, exception) -> response.sendError(401, "Credenciais inválidas"))
//                 )
//                 .logout(logout -> logout
//                         .logoutUrl("/logout")
//                         .logoutSuccessHandler((request, response, authentication) -> response.setStatus(200))
//                 )
//                 .exceptionHandling(exception -> exception
//                         .accessDeniedPage("/acesso-negado") // Página personalizada para acesso negado
//                 )
//                 .authenticationProvider(authenticationProvider());

//         return http.build();
//     }

//     @Bean
//     public DaoAuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(service);
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return authProvider;
//     }

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }