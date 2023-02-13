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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public FriendService(FriendRepository friendRepository,
                         UserRepository userRepository,
                         AuthService authService) {
        this.friendRepository = friendRepository;
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
    public void saveFriend(Long user_id, Long friend_id) throws NullPointerException {
        if( !(friendRepository.existsByFirstUserAndSecondUser(user_id, friend_id)) )  {
            Friend newFriend = new Friend();
            newFriend.setUserId(user_id);
            newFriend.setFriendId(friend_id);
            friendRepository.save(newFriend);
        }
    }


    public List<FriendDto> findByUserId(Long user_id) {
        return friendRepository.findByFirstUser(user_id).stream()
                .map(FriendMapper.FRIEND_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FriendDto create(FriendDto friends) {
        Friend entity = FriendMapper.FRIEND_MAPPER.mapFromDto(friends);
        friendRepository.save(entity);
        return FriendMapper.FRIEND_MAPPER.mapToDto(entity);
    }

    public void delete(long id) {
        Friend entity = friendRepository.getReferenceById(id);
        friendRepository.delete(entity);
    }

}
