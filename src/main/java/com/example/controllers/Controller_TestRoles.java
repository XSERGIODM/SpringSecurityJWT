package com.example.controllers;

import com.example.util.Util_ERole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller_TestRoles {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Hola has accedido con el rol de ".concat(Util_ERole.ADMIN.name());
    }

    @GetMapping("/accessInvited")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'INVITED')")
    public String accessInvited() {
        return "Hola has accedido con el rol de ".concat(Util_ERole.INVITED.name());
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String accessUser() {
        return "Hola has accedido con el rol de ".concat(Util_ERole.USER.name());
    }
}
