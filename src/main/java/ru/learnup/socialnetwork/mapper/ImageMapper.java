package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.dto.ImageDto;
import ru.learnup.socialnetwork.view.ImageView;

import java.io.IOException;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageMapper IMAGE_MAPPER = Mappers.getMapper(ImageMapper.class);

    Image toImageEntity(MultipartFile file) throws IOException;

    Image mapToEntity(ImageDto dto);

    ImageDto mapToDto(Image user);

    ImageView mapToView(ImageDto dto);

    ImageDto mapFromView(ImageView view);


}
