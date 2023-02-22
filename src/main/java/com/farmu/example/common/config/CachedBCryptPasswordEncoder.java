package com.farmu.example.common.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CachedBCryptPasswordEncoder extends BCryptPasswordEncoder {

    public CachedBCryptPasswordEncoder(int strength) {
        super(strength);
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        return super.matches(rawPassword, encodedPassword);
    }

}