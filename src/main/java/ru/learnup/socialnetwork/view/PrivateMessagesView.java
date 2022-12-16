package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrivateMessagesView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private LocalDateTime time;
    @JsonProperty
    private String content;
    @JsonProperty
    private UserView from;
    @JsonProperty
    private UserView to;

}
