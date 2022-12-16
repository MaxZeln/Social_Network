package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.mapper.UserMapperInt;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.reposiory.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getUsers() {
        return repository.findAll().stream()
                .map(UserMapper.USER_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(long id) {
        return UserMapper.USER_MAPPER.mapToDto(repository.getReferenceById(id));
    }

    public UserDto findByUserNickname(String nickname) {
        return UserMapper.USER_MAPPER.mapToDto(repository.findUserByLogin(nickname));
    }

    @Transactional
    public UserDto create(UserDto user) {
        User entity = UserMapper.USER_MAPPER.mapFromDto(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        repository.save(entity);
        return UserMapper.USER_MAPPER.mapToDto(entity);
    }

    public void updateUser(UserDto dto) {
        User entity = repository.getReferenceById(dto.getId());
        UserMapperInt.USER_MAPPER_INT.updateUserFromDto(dto, entity);
        repository.save(entity);
    }

    public void delete(long id) {
        User entity = repository.getReferenceById(id);
        repository.delete(entity);
    }
}
