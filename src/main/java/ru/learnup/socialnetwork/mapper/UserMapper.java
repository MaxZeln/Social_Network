package ru.learnup.socialnetwork.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.view.UserView;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;
    private final ImageMapper imageMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper,
             ImageMapper imageMapper) {
        this.roleMapper = roleMapper;
        this.imageMapper = imageMapper;
    }

    public UserDto mapToDto(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .enabled(entity.getEnabled())
                .role(roleMapper.mapToDto(entity.getRole()))
                .image(imageMapper.mapToDto(entity.getImage()))
                .build();
    }

    public User mapToEntity(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setNickname(dto.getNickname());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        user.setEnabled(dto.getEnabled());
        user.setRole(roleMapper.mapToEntity(dto.getRole()));
        user.setImage(imageMapper.mapToEntity(dto.getImage()));
        return user;
    }

    public UserView mapToView(UserDto dto){
        UserView view = new UserView();
        view.setId(dto.getId());
        view.setNickname(dto.getNickname());
        view.setPassword(dto.getPassword());
        view.setStatus(dto.getStatus());
        view.setEnabled(dto.getEnabled());
        view.setRole(roleMapper.mapToView(dto.getRole()));
        view.setImage(imageMapper.mapToView(dto.getImage()));
        return view;
    }

    public UserDto mapFromView(UserView view){
        return UserDto.builder()
                .id(view.getId())
                .nickname(view.getNickname())
                .password(view.getPassword())
                .status(view.getStatus())
                .enabled(view.getEnabled())
                .role(roleMapper.mapFromView(view.getRole()))
                .image(imageMapper.mapFromView(view.getImage()))
                .build();
    }

}
