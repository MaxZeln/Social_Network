package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.socialnetwork.entity.PrivateMessages;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.mapper.PrivateMessagesMapper;
import ru.learnup.socialnetwork.model.PrivateMessagesDto;
import ru.learnup.socialnetwork.reposiory.PrivateIMessagesRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateMessagesService {

    private final PrivateIMessagesRepository repository;
    private final PrivateMessagesMapper mapper;

    @Autowired
    public PrivateMessagesService(PrivateIMessagesRepository repository,
                                  PrivateMessagesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PrivateMessagesDto> getMessages() {
        return repository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PrivateMessagesDto> getMessagesFrom(User from_id) {
        return repository.findAllByFrom_id(from_id).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PrivateMessagesDto> getMessagesFromAndTo(User from_id, User to_id) {
        return repository.findAllByFrom_idAndToo_id(from_id, to_id).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrivateMessagesDto create(PrivateMessagesDto message) {
        PrivateMessages entity = mapper.mapFromDto(message);
        LocalDateTime time = LocalDateTime.now();
        entity.setTime(time);
        repository.save(entity);
        return mapper.mapToDto(entity);
    }

    public void delete(long id) {
        PrivateMessages message = repository.getReferenceById(id);
        repository.delete(message);
    }

}
