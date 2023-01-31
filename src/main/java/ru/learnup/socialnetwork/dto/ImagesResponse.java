package ru.learnup.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagesResponse {

    private String id;
    private String name;
    private String contentType;
    private Long size;
    private String url;

}
