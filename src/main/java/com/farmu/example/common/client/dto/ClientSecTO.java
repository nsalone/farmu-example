package com.farmu.example.common.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.farmu.example.common.utils.ValidationUtils.checkState;
import static java.lang.Boolean.TRUE;


@Data
@AllArgsConstructor
public class ClientSecTO implements UserDetails {

    private final ClientTO client;

    @Override
    public String getUsername() {
        checkState(client, "client");
        return client.getUsername();
    }

    @Override
    public String getPassword() {
        checkState(client, "client");
        return client.getPassword();
    }

    @Override
    public boolean isEnabled() {
        checkState(client, "client");
        return client.getEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return TRUE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}