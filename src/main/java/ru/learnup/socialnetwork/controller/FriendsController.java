package ru.learnup.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.FriendMapper;
import ru.learnup.socialnetwork.reposiory.UserRepository;
import ru.learnup.socialnetwork.service.FriendService;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.view.FriendView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FriendService friendsService;

    @Autowired
    public FriendsController(UserService userService,
                             UserRepository userRepository,
                             FriendService service) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.friendsService = service;
    }

    @GetMapping("/{userid}")
    public List<FriendView> getFriends(@PathVariable(name = "userid") long userid) {
        User user = userRepository.getReferenceById(userid);
        return friendsService.findByUserId(user).stream()
                .map(FriendMapper.FRIEND_MAPPER::mapToView)
                .collect(Collectors.toList());
    }



//    @PostMapping
//    public FriendView AddFriend(@RequestBody FriendView friendsView) {
//        FriendDto friendsDto = mapper.mapFromView(friendsView);
//        return mapper.mapToView(
//                friendsService.create(friendsDto)
//        );
//    }

//    @GetMapping
//    public ResponseEntity<?> AddFriend(@RequestParam("friendId") long friendId) throws NullPointerException {
////        UserDto currentUser = securityService.getUser();
////        friendsService.
////        return ResponseEntity.ok("Friend added successfully");
////        return mapper.mapToView(
////                friendsService.create(friendsDto)
////        );
//    }


}
