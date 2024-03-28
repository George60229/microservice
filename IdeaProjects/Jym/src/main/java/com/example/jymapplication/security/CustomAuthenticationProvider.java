package com.example.jymapplication.security;

import com.example.jymapplication.service.MyUserDetailsService;
import com.example.jymapplication.utils.PasswordHashing;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final MyUserDetailsService userDetailsService;

    public CustomAuthenticationProvider(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        password = PasswordHashing.hashPassword(password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!Objects.equals(password, userDetails.getPassword())) {
            userDetailsService.handleLoginAttempt(username, password);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

