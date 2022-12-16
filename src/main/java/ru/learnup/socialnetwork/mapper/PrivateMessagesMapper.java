package ru.learnup.socialnetwork.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.socialnetwork.entity.PrivateMessages;
import ru.learnup.socialnetwork.model.PrivateMessagesDto;
import ru.learnup.socialnetwork.view.PrivateMessagesView;

@Component
public class PrivateMessagesMapper {

    private final UserMapper userMapper;

    @Autowired
    public PrivateMessagesMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PrivateMessagesDto mapToDto(PrivateMessages entity){
        return PrivateMessagesDto.builder()
                .id(entity.getId())
                .time(entity.getTime())
                .content(entity.getContent())
                .from(userMapper.mapToDto(entity.getFrom()))
                .to(userMapper.mapToDto(entity.getToo()))
                .build();
    }

    public PrivateMessages mapFromDto(PrivateMessagesDto dto){
        PrivateMessages privateMessages = new PrivateMessages();
        privateMessages.setId(dto.getId());
        privateMessages.setTime(dto.getTime());
        privateMessages.setContent(dto.getContent());
        privateMessages.setFrom(userMapper.mapToEntity(dto.getFrom()));
        privateMessages.setToo(userMapper.mapToEntity(dto.getTo()));
        return privateMessages;
    }

    public PrivateMessagesView mapToView(PrivateMessagesDto dto){
        PrivateMessagesView view = new PrivateMessagesView();
        view.setId(dto.getId());
        view.setTime(dto.getTime());
        view.setContent(dto.getContent());
        view.setFrom(userMapper.mapToView(dto.getFrom()));
        view.setTo(userMapper.mapToView(dto.getTo()));
        return view;
    }

    public PrivateMessagesDto mapFromView(PrivateMessagesView view){
        return PrivateMessagesDto.builder()
                .id(view.getId())
//                .time(view.getTime())
                .time(view.getTime())
                .content(view.getContent())
                .from(userMapper.mapFromView(view.getFrom()))
                .to(userMapper.mapFromView(view.getTo()))
                .build();
    }
}
