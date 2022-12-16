package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.dto.UserDto;

@Component
@Mapper(componentModel = "spring")
public interface UserMapperInt {

    UserMapperInt USER_MAPPER_INT= Mappers.getMapper(UserMapperInt.class);

    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
