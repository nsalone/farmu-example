package com.farmu.example.common.utils;

import com.farmu.example.common.client.dto.ClientSecTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {

    public static Optional<String> findUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof ClientSecTO)) {
            return Optional.empty();
        }

        return Optional.of(((ClientSecTO) authentication.getPrincipal()).getUsername());
    }

    public static String getUsername() {
        return findUser().orElse(null);
    }
}
