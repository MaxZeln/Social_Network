package ru.learnup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.learnup.socialnetwork.entity.Image;
import ru.learnup.socialnetwork.model.ImagesResponse;
import ru.learnup.socialnetwork.view.ImagesResponseView;

@Component
public class ImageResponseMapper {

    public ImagesResponse mapToImagesResponse(Image image) {


        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/")
                .path(image.getId())
                .toUriString();

        return ImagesResponse.builder()
                .id(image.getId())
                .name(image.getName())
                .contentType(image.getContentType())
                .size(image.getSize())
                .url(downloadURL)
                .build();
    }

    public ImagesResponseView mapToView(ImagesResponse dto) {
        ImagesResponseView view = new ImagesResponseView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setSize(dto.getSize());
        view.setContentType(dto.getContentType());
        view.setUrl(dto.getUrl());
        return view;
    }

    public ImagesResponse mapFromView(ImagesResponseView view) {
        return ImagesResponse.builder()
                .id(view.getId())
                .name(view.getName())
                .size(view.getSize())
                .contentType(view.getContentType())
                .url(view.getUrl())
                .build();
    }


}
