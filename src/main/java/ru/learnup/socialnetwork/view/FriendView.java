package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FriendView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private UserView userId;
    @JsonProperty
    private UserView friendId;

}
