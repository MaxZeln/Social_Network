package ru.learnup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.entity.Role;
import ru.learnup.socialnetwork.model.RoleDto;
import ru.learnup.socialnetwork.view.RoleView;

@Component
public class RoleMapper {

    public RoleDto mapToDto(Role entity) {
        return RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Role mapToEntity(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    public RoleView mapToView(RoleDto dto) {
        RoleView view = new RoleView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        return view;
    }

    public RoleDto mapFromView(RoleView view) {
        return RoleDto.builder()
                .id(view.getId())
                .name(view.getName())
                .build();
    }
}
