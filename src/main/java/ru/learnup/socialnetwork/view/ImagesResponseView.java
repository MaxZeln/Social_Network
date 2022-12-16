package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImagesResponseView {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String contentType;
    @JsonProperty
    private Long size;
    @JsonProperty
    private String url;

}
