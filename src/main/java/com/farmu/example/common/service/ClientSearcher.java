package com.farmu.example.common.service;

import com.farmu.example.common.client.dto.ClientSecTO;
import com.farmu.example.common.client.service.ClientService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.farmu.example.common.utils.ValidationUtils.hasContent;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
public class ClientSearcher implements UserDetailsService {

    @Autowired
    private ClientService service;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        if (!hasContent(username)) {
            throw new InsufficientAuthenticationException("Username is mandatory");
        }
        return ofNullable(service.findByUsername(username))
                .map(ClientSecTO::new)
                .orElseThrow(() -> new InsufficientAuthenticationException(format("Client with name '%s' not found", username)));
    }

}
