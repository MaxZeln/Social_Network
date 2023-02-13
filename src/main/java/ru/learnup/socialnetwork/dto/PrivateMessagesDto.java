package ru.learnup.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessagesDto {

    private Long id;
    private LocalDateTime time;
    private String content;
    private Long fromUserId;
    private Long tooUserId;

}
