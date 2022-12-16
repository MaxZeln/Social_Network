package ru.learnup.socialnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.reposiory.UserRepository;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByNickname(nickname);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(user.get());
    }

//    public User getUser() {
//        User userPrincipal = null;
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Object principal = securityContext.getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            userPrincipal =((User) principal);
//        }
//        return userPrincipal;
//    }

}
