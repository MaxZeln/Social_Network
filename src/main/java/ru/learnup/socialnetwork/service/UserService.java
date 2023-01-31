package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.mapper.UserMapperInt;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.reposiory.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.USER_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

//    public UserDto findById(long id) {
//
//        return UserMapper.USER_MAPPER.mapToDto(userRepository.getReferenceById(id));
//    }

    public Optional<UserDto> findById(long id) {
        UserDto userDto = UserMapper.USER_MAPPER.mapToDto(userRepository.getReferenceById(id));

        Optional<UserDto> userDtoOptional = Optional.of(userDto);

        if (userDtoOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDtoOptional;
    }










    public UserDto findByUserNickname(String nickname) {
        return UserMapper.USER_MAPPER.mapToDto(userRepository.findUserByLogin(nickname));
    }

    public Optional<User> defineUserByNickname(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.searchUserByLogin(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return user;
    }

    @Transactional
    public UserDto create(UserDto user) {
        User entity = UserMapper.USER_MAPPER.mapFromDto(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        return UserMapper.USER_MAPPER.mapToDto(entity);
    }

    public void updateUser(UserDto dto) {
        User entity = userRepository.getReferenceById(dto.getId());
        UserMapperInt.USER_MAPPER_INT.updateUserFromDto(dto, entity);
        userRepository.save(entity);
    }

    public void delete(long id) {
        User entity = userRepository.getReferenceById(id);
        userRepository.delete(entity);
    }
}
