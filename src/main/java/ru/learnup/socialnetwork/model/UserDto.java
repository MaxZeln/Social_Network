package ru.learnup.socialnetwork.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String nickname;
    private String password;
    private String status;
    private Boolean enabled;
    private RoleDto role;
    private ImageDto image;

}
