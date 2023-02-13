package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.mapper.FriendMapper;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.model.Friend;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.dto.FriendDto;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.reposiory.FriendRepository;
import ru.learnup.socialnetwork.reposiory.UserRepository;
import ru.learnup.socialnetwork.security.AuthService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public FriendService(FriendRepository repository,
                         UserRepository userRepository,
                         AuthService authService) {
        this.friendRepository = repository;
        this.userRepository = userRepository;
        this.authService = authService;
    }


    public List<FriendDto> getAllFriends() {
        return friendRepository.findAll().stream()
                .map(FriendMapper.FRIEND_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    public FriendDto findById(long id) {
        return FriendMapper.FRIEND_MAPPER.mapToDto(friendRepository.getReferenceById(id));
    }


    @Transactional
    public void saveFriend(UserDto userDto1, long id) throws NullPointerException {

        User user = userRepository.getReferenceById(id);
        UserDto userDto2 = UserMapper.USER_MAPPER.mapToDto(user);

        User user1 = userRepository.findUserByLogin(userDto1.getLogin());
        User user2 = userRepository.findUserByLogin(userDto2.getLogin());

        if( !(friendRepository.existsByFirstUserAndSecondUser(user1, user2)) )  {
            Friend friend = new Friend();
            friend.setUserId(user1.getId());
            friend.setFriendId(user2.getId());
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
                .map(FriendMapper.FRIEND_MAPPER::mapToDto)
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
