package ru.learnup.socialnetwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.UserView;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final UserService service;
    private final UserMapper userMapper;

    public SearchController(UserService service,
                            UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @GetMapping("/userbyid")
    public UserView getUserById(@PathVariable(name = "userid") int userid) {
        UserDto userDto = service.findById(userid);
        return userMapper.mapToView(userDto);
    }

    @GetMapping("/userbynicknamel")
    public UserView getUserByNickname(@PathVariable(name = "nickname") String nickname) {
        UserDto userDto = service.findByUserNickname(nickname);
        return userMapper.mapToView(userDto);
    }


}
