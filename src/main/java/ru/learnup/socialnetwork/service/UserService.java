package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.mapper.UserMapperInt;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.reposiory.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserMapperInt userMapperInt;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       UserMapper userMapper,
                       UserMapperInt userMapperInt,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.userMapperInt = userMapperInt;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getUsers() {
        return repository.findAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(long id) {
        return userMapper.mapToDto(repository.getReferenceById(id));
    }

    public UserDto findByUserNickname(String nickname) {
        return userMapper.mapToDto(repository.findUserByNickname(nickname));
    }

    @Transactional
    public UserDto create(UserDto user) {
        User entity = userMapper.mapToEntity(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        repository.save(entity);
        return userMapper.mapToDto(entity);
    }

    public void updateUser(UserDto dto) {
        User entity = repository.getReferenceById(dto.getId());
        userMapperInt.updateUserFromDto(dto, entity);
        repository.save(entity);
    }

    public void delete(long id) {
        User entity = repository.getReferenceById(id);
        repository.delete(entity);
    }
}
