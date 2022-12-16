package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageView {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String originalFileName;
    @JsonProperty
    private Long size;
    @JsonProperty
    private byte[] bytes;
    @JsonProperty
    private String contentType;

}
