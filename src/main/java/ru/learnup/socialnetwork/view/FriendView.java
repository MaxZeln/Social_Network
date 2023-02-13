package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FriendView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private Long userId;
    @JsonProperty
    private Long friendId;

}
