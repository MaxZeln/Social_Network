package ru.learnup.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {

    private String id;
    private String name;
    private String originalFileName;
    private Long size;
    private byte[] bytes ;
    private String contentType;

}
