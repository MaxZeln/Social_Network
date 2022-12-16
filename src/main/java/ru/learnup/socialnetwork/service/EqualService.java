package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.reposiory.UserRepository;

import java.util.Optional;

@Service
public class EqualService {

    private final UserRepository userRepository;

    @Autowired
    public EqualService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> defineUserByNickname(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByNickname(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return user;
    }
}
