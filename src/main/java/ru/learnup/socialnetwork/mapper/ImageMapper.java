package ru.learnup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.learnup.socialnetwork.entity.Image;
import ru.learnup.socialnetwork.model.ImageDto;
import ru.learnup.socialnetwork.view.ImageView;

import java.io.IOException;

@Component
public class ImageMapper {

    public Image toImageEntity(MultipartFile file) throws IOException {
        Image imageEntity = new Image();
        imageEntity.setName(file.getName());
        imageEntity.setOriginalFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imageEntity.setContentType(file.getContentType());
        imageEntity.setBytes(file.getBytes());
        imageEntity.setSize(file.getSize());
        return imageEntity;
    }

    public ImageDto mapToDto(Image entity) {
        return ImageDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .originalFileName(entity.getOriginalFileName())
                .size(entity.getSize())
                .bytes(entity.getBytes())
                .contentType(entity.getContentType())
                .build();
    }

    public Image mapToEntity(ImageDto dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setName(dto.getName());
        image.setOriginalFileName(dto.getOriginalFileName());
        image.setSize(dto.getSize());
        image.setContentType(dto.getContentType());
        image.setBytes(dto.getBytes());
        return image;
    }

    public ImageView mapToView(ImageDto dto) {
        ImageView view = new ImageView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setOriginalFileName(dto.getOriginalFileName());
        view.setSize(dto.getSize());
        view.setContentType(dto.getContentType());
        view.setBytes(dto.getBytes());
        return view;
    }

    public ImageDto mapFromView(ImageView view) {
        return ImageDto.builder()
                .id(view.getId())
                .name(view.getName())
                .originalFileName(view.getOriginalFileName())
                .size(view.getSize())
                .contentType(view.getContentType())
                .bytes(view.getBytes())
                .build();
    }

}
