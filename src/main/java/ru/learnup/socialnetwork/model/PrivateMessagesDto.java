package ru.learnup.socialnetwork.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PrivateMessagesDto {

    private Long id;
    private LocalDateTime time;
    private String content;
    private UserDto from;
    private UserDto to;

}
