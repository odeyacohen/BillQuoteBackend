package com.billquote.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.billquote.security.JpaUserDetailsService;
import com.billquote.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // ===== Password encoder =====
   
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();}
   
	
    @Bean
    public AuthenticationProvider authenticationProvider(JpaUserDetailsService uds,
                                                         PasswordEncoder encoder) {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(uds);
        dao.setPasswordEncoder(encoder);
        return dao;
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // ===== Sécurité HTTP =====
    
 
    @Bean
    public SecurityFilterChain security(HttpSecurity http,
                                        JwtFilter jwtFilter,
                                        AuthenticationProvider provider) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(provider)
            .authorizeHttpRequests(auth -> auth
                // Racine / erreurs (optionnel)
                .requestMatchers("/", "/error", "/ping").permitAll()

                // Swagger en public
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // Auth en public (login, register...)
                .requestMatchers("/auth/**", "/api/auth/**").permitAll()

                // FACTURES
                // Lecture publique (comme tu l’avais déjà)
                .requestMatchers(HttpMethod.GET, "/api/factures/**").permitAll()
                // Création / modif / suppression de facture = utilisateur connecté
                .requestMatchers(HttpMethod.POST, "/api/factures/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/api/factures/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/factures/**").authenticated()

                // DEVIS : lecture publique (si tu veux), écriture = connecté
                .requestMatchers(HttpMethod.GET, "/api/devis/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/devis/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/api/devis/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/devis/**").authenticated()

                // CLIENT : à adapter, ici je mets lecture publique comme tu l’avais
                .requestMatchers(HttpMethod.GET, "/api/client/**").permitAll()

                // Le reste de l'API doit être authentifié
                .anyRequest().authenticated()
            )
            // JWT avant UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ===== CORS =====
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("*")); // en prod: remplace par ton domaine/IP
        cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        cors.setAllowedHeaders(List.of("*"));
        cors.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
}




//package com.billquote.config;
//
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import com.billquote.security.JwtFilter;
//
//import org.springframework.security.core.userdetails.User;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtFilter jwtFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user = User.withUsername("user@example.com")
//                .password(encoder.encode("password"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//    @Bean
//    SecurityFilterChain security(HttpSecurity http) throws Exception {
//      return http
//        .csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(auth -> auth
//          .requestMatchers("/actuator/**",  "/api/clients/**","/api/factures/**","/api/devis/**","/api/societes/**", "/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//          .requestMatchers("/api/factures/**").permitAll() 
//           .requestMatchers("/api/societes/**").permitAll()
//           .requestMatchers("/api/auth/**").permitAll()
//          .requestMatchers(HttpMethod.GET, "/").permitAll()   // autoriser la racine si tu veux l’ouvrir
//          .anyRequest().authenticated()
//        )
//        .build();
//    }
//    
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//      CorsConfiguration cors = new CorsConfiguration();
//      cors.setAllowedOrigins(List.of("*")); // en prod: mets tes domaines ou IP
//      cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
//      cors.setAllowedHeaders(List.of("*"));
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/api/**", cors);
//      return source;
//    
//  }
////    @RequestMapping
////    
////
////      @Bean
////      SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.csrf(csrf -> csrf.disable())
////            .authorizeHttpRequests(auth -> auth
////                .requestMatchers(
////                    "/v3/api-docs/**",
////                    "/swagger-ui.html",
////                    "/swagger-ui/**"
////                ).permitAll()
////                .requestMatchers("/api/auth/**").permitAll()
////                .anyRequest().authenticated()
////            );
////        return http.build();
////      }
//    
//
//
//} 
