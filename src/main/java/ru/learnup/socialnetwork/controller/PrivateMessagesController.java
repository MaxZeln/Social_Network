package ru.learnup.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.socialnetwork.mapper.PrivateMessagesMapper;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.dto.PrivateMessagesDto;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.service.PrivateMessagesService;
import ru.learnup.socialnetwork.view.PrivateMessagesView;
import ru.learnup.socialnetwork.view.UserView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/privateMessages")
public class PrivateMessagesController {

    private final PrivateMessagesService service;

    @Autowired
    public PrivateMessagesController(PrivateMessagesService service) {
        this.service = service;
    }

    @PostMapping
    public PrivateMessagesView createMessage(@RequestBody PrivateMessagesView view) {
        PrivateMessagesDto dto = PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER.mapFromView(view);
        return PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER.mapToView(
                service.create(dto)
        );
    }

    @GetMapping("/allMessages")
    public List<PrivateMessagesView> getAllMessages() {
        List<PrivateMessagesDto> messages = service.getMessages();
        return messages.stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/messagesFrom")
    public List<PrivateMessagesView> getMessagesFrom(@RequestBody Long userId) {
        List<PrivateMessagesDto> messages = service.getMessagesFrom(userId);
        return messages.stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/messagesFromAndTo")
    public List<PrivateMessagesView> getMessagesFromAndTo(@RequestBody Long fromUserId,
                                                          @RequestBody Long toUserId) {
        List<PrivateMessagesDto> messages = service.getMessagesFromAndTo(fromUserId, toUserId);
        return messages.stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToView)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{imageId}")
    public void delete(@PathVariable(name = "imageId") long imageId) {
        service.delete(imageId);
    }


}
