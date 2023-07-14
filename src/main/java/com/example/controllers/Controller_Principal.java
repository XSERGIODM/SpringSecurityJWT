package com.example.controllers;

import com.example.dto.Dto_User;
import com.example.models.Model_Role;
import com.example.models.Model_User;
import com.example.repositories.Repository_Role;
import com.example.repositories.Repository_User;
import com.example.util.Util_ERole;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller_Principal {

    Repository_User repositoryUser;
    PasswordEncoder passwordEncoder;


    @GetMapping("/hello")
    public String hello() {
        return "Hello world Not secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured() {
        return "Hello world Secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Dto_User dtoUser) {

        Set<Model_Role> roles = dtoUser.getRoles().stream()
                .map(role -> Model_Role.builder()
                        .name(Util_ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Model_User modelUser = Model_User.builder()
                .username(dtoUser.getUsername())
                .password(passwordEncoder.encode(dtoUser.getPassword()))
                .email(dtoUser.getEmail())
                .roles(roles)
                .build();

        return new ResponseEntity<>(repositoryUser.save(modelUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String ide){
        repositoryUser.deleteById(Long.parseLong(ide));
        return "Se ha borrado el usuario con id ".concat(ide);
    }
}
