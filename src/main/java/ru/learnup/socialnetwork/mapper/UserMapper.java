package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.dto.RoleDto;
import ru.learnup.socialnetwork.model.Role;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.view.UserView;

import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User mapFromDto(UserDto dto);

    @Mapping(target="confirmPassword", source="user.password")
    UserDto mapToDto(User user);





    UserView mapToView(UserDto dto);

    UserDto mapFromView(UserView view);

}
