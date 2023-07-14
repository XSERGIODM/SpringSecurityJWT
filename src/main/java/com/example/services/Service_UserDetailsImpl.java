package com.example.services;

import com.example.models.Model_User;
import com.example.repositories.Repository_User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Service_UserDetailsImpl implements UserDetailsService {

    Repository_User repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Model_User modelUser = repositoryUser.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));

        Collection<? extends GrantedAuthority> grantedAuthorities = modelUser.getRoles().stream()
                .map(modelRole -> new SimpleGrantedAuthority("ROLE_".concat(modelRole.getName().name())))
                .collect(Collectors.toSet());

        return new User(modelUser.getUsername(),
                modelUser.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }
}
