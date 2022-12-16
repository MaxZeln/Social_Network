package ru.learnup.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.mapper.PrivateMessagesMapper;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.model.PrivateMessagesDto;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.service.PrivateMessagesService;
import ru.learnup.socialnetwork.view.PrivateMessagesView;
import ru.learnup.socialnetwork.view.UserView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/privateMessages")
public class PrivateMessagesController {

    private final PrivateMessagesService service;
    private final PrivateMessagesMapper mapper;
    private final UserMapper userMapper;

    @Autowired
    public PrivateMessagesController(PrivateMessagesService service,
                                     PrivateMessagesMapper mapper,
                                     UserMapper userMapper) {
        this.service = service;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @PostMapping
    public PrivateMessagesView createMessage(@RequestBody PrivateMessagesView view) {
        PrivateMessagesDto dto = mapper.mapFromView(view);
        return mapper.mapToView(
                service.create(dto)
        );
    }

    @GetMapping("/allMessages")
    public List<PrivateMessagesView> getAllMessages() {
        List<PrivateMessagesDto> messages = service.getMessages();
        return messages.stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/messagesFrom")
    public List<PrivateMessagesView> getMessagesFrom(@RequestBody UserView userView) {
        UserDto userDto = userMapper.mapFromView(userView);
        User entity = userMapper.mapToEntity(userDto);
        List<PrivateMessagesDto> messages = service.getMessagesFrom(entity);
        return messages.stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/messagesFromAndTo")
    public List<PrivateMessagesView> getMessagesFromAndTo(@RequestBody UserView from,
                                                          @RequestBody UserView to) {
        UserDto fromUserDto = userMapper.mapFromView(from);
        User fromEntity = userMapper.mapToEntity(fromUserDto);
        UserDto toUserDto = userMapper.mapFromView(to);
        User toEntity = userMapper.mapToEntity(toUserDto);
        List<PrivateMessagesDto> messages = service.getMessagesFromAndTo(fromEntity, toEntity);
        return messages.stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{imageId}")
    public void delete(@PathVariable(name = "imageId") long imageId) {
        service.delete(imageId);
    }


}
