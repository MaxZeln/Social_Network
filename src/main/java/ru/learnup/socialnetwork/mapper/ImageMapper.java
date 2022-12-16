package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.dto.ImageDto;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.view.ImageView;
import ru.learnup.socialnetwork.view.UserView;

import java.io.IOException;

@Mapper
public interface ImageMapper {

    ImageMapper IMAGE_MAPPER = Mappers.getMapper(ImageMapper.class);

    Image toImageEntity(MultipartFile file) throws IOException;

    Image mapToEntity(ImageDto dto);

    ImageDto mapToDto(Image user);

    ImageView mapToView(ImageDto dto);

    ImageDto mapFromView(ImageView view);


}
