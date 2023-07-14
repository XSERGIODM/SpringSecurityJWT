package com.example.security;

import com.example.security.filters.Filter_JwtAuthentication;
import com.example.security.jwt.Jwt_Util;
import com.example.services.Service_UserDetailsImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Security_Config {

    Service_UserDetailsImpl userDetails;
    Jwt_Util jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager manager) throws Exception {

        Filter_JwtAuthentication filterJwtAuthentication = new Filter_JwtAuthentication(jwtUtil);
        filterJwtAuthentication.setAuthenticationManager(manager);
        //Para cambiar la url de /login es asi
        //filterJwtAuthentication.setFilterProcessesUrl("/URL ue yo quiera");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/hello").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(filterJwtAuthentication)
                .build();
    }

  /*
  Para crear un usuario en memoria
  @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("sergio")
                .password("12345")
                .roles()
                .build());
        return manager;
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetails)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }

    /*
    Hice este main para apoyarme y crear una contraseña encriptada y cambiarla al usuario que tenía en la BD
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("12345"));
    }*/
}
