package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.learnup.socialnetwork.model.Role;
import ru.learnup.socialnetwork.dto.RoleDto;
import ru.learnup.socialnetwork.view.RoleView;

import java.util.Optional;

@Mapper
public interface RoleMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    Role mapFromDto(RoleDto dto);

    RoleDto mapToDto(Role role);


    RoleView mapToView(RoleDto dto);

    RoleDto mapFromView(RoleView view);

}
