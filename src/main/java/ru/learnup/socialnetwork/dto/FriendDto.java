package ru.learnup.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendDto {

    private Long id;
    private UserDto userId;
    private UserDto friendId;

}
