package ru.learnup.socialnetwork.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FriendDto {

    private Long id;
    private UserDto userId;
    private UserDto friendId;

}
