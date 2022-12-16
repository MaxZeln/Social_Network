package ru.learnup.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;

    private String login;

    private String email;

    private String phone;

    private String password;

    private String confirmPassword;

    private String status;

    private Boolean enabled;

    private RoleDto role;

    private ImageDto image;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickname='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", status='" + status + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", image=" + image +
                '}';
    }
}
