package ru.learnup.socialnetwork.controller;


import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.mapper.UserMapperInt;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.UserView;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
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
    public ModelAndView getUserProfile(@PathVariable(name = "userid") int userid) throws UnsupportedEncodingException {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        System.out.println(auth.getPrincipal());

        Optional<UserDto> userDto = service.findById(userid);

        ModelAndView profile = new ModelAndView("/profile/profile");

        if (userDto.get().getImage() != null) {

            byte[] encode = Base64.encode(userDto.get().getImage().getBytes());

            profile.addObject("image", new String(encode, "UTF-8"));
        }

        return profile;

    }

//    @GetMapping("/{userid}")
//    public UserView getUser(@PathVariable(name = "userid") int userid) {
//        UserDto userDto = service.findById(userid);
//        return UserMapper.USER_MAPPER.mapToView(userDto);
//    }

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
