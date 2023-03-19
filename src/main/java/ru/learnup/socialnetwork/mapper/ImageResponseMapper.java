package ru.learnup.socialnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.dto.ImagesResponse;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.view.ImagesResponseView;
import ru.learnup.socialnetwork.view.UserView;

@Mapper(componentModel = "spring")
public interface ImageResponseMapper {

    ImageResponseMapper IMAGES_RESPONSE = Mappers.getMapper(ImageResponseMapper.class);

    ImagesResponseView mapToView(ImagesResponse dto);

    ImagesResponse mapFromView(ImagesResponseView view);

}
