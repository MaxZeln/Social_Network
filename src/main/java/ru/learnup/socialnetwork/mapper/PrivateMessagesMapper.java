package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.PrivateMessages;
import ru.learnup.socialnetwork.dto.PrivateMessagesDto;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.view.PrivateMessagesView;
import ru.learnup.socialnetwork.view.UserView;

@Mapper
public interface PrivateMessagesMapper {

    PrivateMessagesMapper PRIVATE_MESSAGES_MAPPER = Mappers.getMapper(PrivateMessagesMapper.class);

    PrivateMessages mapFromDto(PrivateMessagesDto dto);

    PrivateMessagesDto mapToDto(PrivateMessages user);

    PrivateMessagesView mapToView(PrivateMessagesDto dto);

    PrivateMessagesDto mapFromView(PrivateMessagesView view);

}
