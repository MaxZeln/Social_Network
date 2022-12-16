package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;

}
