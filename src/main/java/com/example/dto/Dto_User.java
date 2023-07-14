package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;


@Value
public class Dto_User implements Serializable {

    @Size(max = 30)
    @NotBlank
    String username;

    @NotBlank
    String password;

    @Size(max = 80)
    @Email
    @NotBlank
    String email;

    Set<String> roles;
}