package ru.learnup.socialnetwork.controller;


import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.UserView;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService service,
                          UserMapper userMapper) {
        this.userService = service;
        this.userMapper = userMapper;
    }

    @GetMapping("/allUsers")
    public List<UserView> getUsers() {
        List<UserDto> users = userService.getUsers();
        return users.stream()
                .map(UserMapper.USER_MAPPER::mapToView)
                .collect(Collectors.toList());

    }

    @GetMapping("/{userid}")
    public ModelAndView getUserProfile(@PathVariable(name = "userid") int userId) throws UnsupportedEncodingException {

        Optional<UserDto> userDto = userService.findById(userId);

        ModelAndView profile = new ModelAndView("/profile/profile");

        if (userDto.get().getImage() != null) {
            byte[] encode = Base64.encode(userDto.get().getImage().getBytes());
            profile.addObject("image", new String(encode, "UTF-8"));
        }

        return profile;
    }

    @GetMapping("/json/{userid}")
    public UserView getUser(@PathVariable(name = "userid") int userid) {
        Optional<UserDto> userDto = userService.findById(userid);
        return UserMapper.USER_MAPPER.mapToView(userDto.get());
    }


    @PostMapping
    public UserView createUser(@RequestBody UserView userView) {
        UserDto userDto = UserMapper.USER_MAPPER.mapFromView(userView);
        return UserMapper.USER_MAPPER.mapToView(
                userService.create(userDto)
        );
    }


    @PutMapping(value = "/update/{userId}")
    public UserView updateUser(@PathVariable(value = "userId") int userId,
                               @RequestBody UserView userView) {
        userService.updateUser(userMapper.USER_MAPPER.mapFromView(userView));
        return userView;
    }


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name = "userId") int userId) {
        userService.delete(userId);
    }

}
