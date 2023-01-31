package ru.learnup.socialnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.reposiory.UserRepository;
import ru.learnup.socialnetwork.security.PersonDetails;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PersonDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.searchUserByLogin(login);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(user.get());
    }

}
