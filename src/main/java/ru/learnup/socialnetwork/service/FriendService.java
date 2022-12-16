package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.entity.Friend;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.mapper.FriendMapper;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.model.FriendDto;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.reposiory.FriendRepository;
import ru.learnup.socialnetwork.reposiory.UserRepository;
import ru.learnup.socialnetwork.security.AuthService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendMapper mapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Autowired
    public FriendService(FriendRepository repository,
                         FriendMapper mapper,
                         UserRepository userRepository,
                         UserMapper userMapper, AuthService authService) {
        this.friendRepository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authService = authService;
    }


    public List<FriendDto> getAllFriends() {
        return friendRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public FriendDto findById(long id) {
        return mapper.mapToDto(friendRepository.getReferenceById(id));
    }


    @Transactional
    public void saveFriend(UserDto userDto1, long id) throws NullPointerException {

        User user = userRepository.getReferenceById(id);
        UserDto userDto2 = userMapper.mapToDto(user);

        User user1 = userRepository.findUserByNickname(userDto1.getNickname());
        User user2 = userRepository.findUserByNickname(userDto2.getNickname());

        if( !(friendRepository.existsByFirstUserAndSecondUser(user1, user2)) )  {
            Friend friend = new Friend();
            friend.setUserId(user1);
            friend.setFriendId(user2);
            friendRepository.save(friend);
        }

    }

//    public List<User> getFriends() {
//
//        User currentUser = authService.getUser();
//
//        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
//        List<User> friendUsers = new ArrayList<>();
//
//
//    }

    public List<FriendDto> findByUserId(User user) {
        return friendRepository.findByFirstUser(user).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

//        return mapper.mapToDto(repository.findFriendsByUserId(user));
    }

//    @Transactional
//    public FriendDto create(FriendDto friends) {
//        Friend entity = mapper.mapToEntity(friends);
//        repository.save(entity);
//        return mapper.mapToDto(entity);
//    }

    public void delete(long id) {
        Friend entity = friendRepository.getReferenceById(id);
        friendRepository.delete(entity);
    }

}
