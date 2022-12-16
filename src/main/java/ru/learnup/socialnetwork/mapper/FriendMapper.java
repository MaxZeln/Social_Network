package ru.learnup.socialnetwork.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.entity.Friend;
import ru.learnup.socialnetwork.model.FriendDto;
import ru.learnup.socialnetwork.view.FriendView;

import java.util.stream.Collectors;

@Component
public class FriendMapper {

    private final UserMapper userMapper;

    @Autowired
    public FriendMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public FriendDto mapToDto(Friend entity) {
        return FriendDto.builder()
                .id(entity.getId())
                .userId(userMapper.mapToDto(entity.getUserId()))
                .friendId(userMapper.mapToDto(entity.getFriendId()))
                .build();
    }

    public Friend mapToEntity(FriendDto dto) {
        Friend friends = new Friend();
        friends.setId(dto.getId());
        friends.setUserId(userMapper.mapToEntity(dto.getUserId()));
        friends.setFriendId(userMapper.mapToEntity(dto.getFriendId()));
       return friends;
    }

    public FriendView mapToView(FriendDto dto) {
        FriendView view = new FriendView();
        view.setId(dto.getId());
        view.setUserId(userMapper.mapToView(dto.getUserId()));
        view.setFriendId(userMapper.mapToView(dto.getFriendId()));
        return view;
    }

    public FriendDto mapFromView(FriendView view) {
        return FriendDto.builder()
                .id(view.getId())
                .userId(userMapper.mapFromView(view.getUserId()))
                .friendId(userMapper.mapFromView(view.getFriendId()))
                .build();
    }

}
