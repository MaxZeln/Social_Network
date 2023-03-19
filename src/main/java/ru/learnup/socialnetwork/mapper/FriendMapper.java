package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.Friend;
import ru.learnup.socialnetwork.dto.FriendDto;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.view.FriendView;
import ru.learnup.socialnetwork.view.UserView;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    FriendMapper FRIEND_MAPPER= Mappers.getMapper(FriendMapper.class);

    Friend mapFromDto(FriendDto dto);

    FriendDto mapToDto(Friend user);

    FriendView mapToView(FriendDto dto);

    FriendDto mapFromView(FriendView view);

}
