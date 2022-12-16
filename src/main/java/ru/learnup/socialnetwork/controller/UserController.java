package ru.learnup.socialnetwork.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.mapper.UserMapperInt;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.UserView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/allUsers")
    public List<UserView> getUsers() {
        List<UserDto> users = service.getUsers();
        return users.stream()
                .map(UserMapper.USER_MAPPER::mapToView)
                .collect(Collectors.toList());

    }

    @GetMapping("/{userid}")
    public UserView getUser(@PathVariable(name = "userid") int userid) {
        UserDto userDto = service.findById(userid);
        return UserMapper.USER_MAPPER.mapToView(userDto);
    }

    @PostMapping
    public UserView createUser(@RequestBody UserView userView) {
        UserDto userDto = UserMapper.USER_MAPPER.mapFromView(userView);
        return UserMapper.USER_MAPPER.mapToView(
                service.create(userDto)
        );
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name = "userId") int userId) {
        service.delete(userId);
    }

}
