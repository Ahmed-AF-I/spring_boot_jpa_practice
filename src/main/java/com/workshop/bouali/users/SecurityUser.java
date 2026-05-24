package com.workshop.bouali.users;

import com.workshop.bouali.models.Employee;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {

    private final Employee employee;

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(employee.getRole().name()));
    }

    @Override
    public @NonNull String getPassword() {
        return employee.getPassword();
    }

    @Override
    public @NonNull String getUsername() {
        return employee.getEmail();
    }
}
