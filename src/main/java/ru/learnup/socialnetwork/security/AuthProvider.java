package ru.learnup.socialnetwork.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Component
public class AuthProvider implements AuthenticationProvider {

    private final AuthService personDetailsService;

    @Autowired
    public AuthProvider(AuthService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        PersonDetails personDetails = personDetailsService.loadUserByUsername(login);

        System.out.println(personDetails.getPerson().getLogin());
        System.out.println(personDetails.getPerson().getEmail());

        if (personDetails == null) {
            throw new BadCredentialsException("Unknown user " + login);
        }
        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        System.out.println(personDetails.getPerson().getEmail());


        return new UsernamePasswordAuthenticationToken(personDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}