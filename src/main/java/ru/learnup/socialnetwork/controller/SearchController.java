package ru.learnup.socialnetwork.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.UserView;

import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final UserService service;

    public SearchController(UserService service) {
        this.service = service;
    }

    @GetMapping("/userbyid")
    public UserView getUserById(@RequestParam(name = "userid") int userid) {
        Optional<UserDto> userDto = service.findById(userid);
        return UserMapper.USER_MAPPER.mapToView(userDto.get());
    }

    @GetMapping("/userbynickname")
    public UserView getUserByNickname(@RequestParam(name = "nickname") String nickname) {
        UserDto userDto = service.findByUserNickname(nickname);
        return UserMapper.USER_MAPPER.mapToView(userDto);
    }


}
