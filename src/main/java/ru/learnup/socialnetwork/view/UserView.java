package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String password;
    @JsonProperty
    private String status;
    @JsonProperty
    private Boolean enabled;
    @JsonProperty
    private ImageView image;
    @JsonProperty
    private RoleView role;

}
