package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.model.UserDto;

@Component
@Mapper(componentModel = "spring")
public interface UserMapperInt {
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
