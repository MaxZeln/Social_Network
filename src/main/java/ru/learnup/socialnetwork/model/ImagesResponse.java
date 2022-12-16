package ru.learnup.socialnetwork.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagesResponse {

    private String id;
    private String name;
    private String contentType;
    private Long size;
    private String url;

}
