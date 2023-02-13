package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.model.PrivateMessages;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.PrivateMessagesMapper;
import ru.learnup.socialnetwork.dto.PrivateMessagesDto;
import ru.learnup.socialnetwork.reposiory.PrivateIMessagesRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateMessagesService {

    private final PrivateIMessagesRepository repository;
    @Autowired
    public PrivateMessagesService(PrivateIMessagesRepository repository) {
        this.repository = repository;
    }

    public List<PrivateMessagesDto> getMessages() {
        return repository.findAll().stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PrivateMessagesDto> getMessagesFrom(Long from_id) {
        return repository.findAllByFrom_id(from_id).stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PrivateMessagesDto> getMessagesFromAndTo(Long from_id, Long to_id) {
        return repository.findAllByFrom_idAndToo_id(from_id, to_id).stream()
                .map(PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrivateMessagesDto create(PrivateMessagesDto message) {
        PrivateMessages entity = PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER.mapFromDto(message);
        LocalDateTime time = LocalDateTime.now();
        entity.setTime(time);
        repository.save(entity);
        return PrivateMessagesMapper.PRIVATE_MESSAGES_MAPPER.mapToDto(entity);
    }

    public void delete(long id) {
        PrivateMessages message = repository.getReferenceById(id);
        repository.delete(message);
    }

}
