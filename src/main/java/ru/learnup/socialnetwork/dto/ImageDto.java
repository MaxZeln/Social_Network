package ru.learnup.socialnetwork.dto;

import lombok.*;

import java.util.Arrays;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private String id;
    private String name;
    private String originalFileName;
    private Long size;
    private byte[] bytes ;
    private String contentType;

    @Override
    public String toString() {
        return "ImageDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", size=" + size +
                ", bytes=" + Arrays.toString(bytes) +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
