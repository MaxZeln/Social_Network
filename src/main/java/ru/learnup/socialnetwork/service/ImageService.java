package ru.learnup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.mapper.ImageMapper;
import ru.learnup.socialnetwork.reposiory.ImageRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository repository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.repository = imageRepository;
    }

    public void save(MultipartFile image) throws IOException {
        repository.save(ImageMapper.IMAGE_MAPPER.toImageEntity(image));
    }

    public Optional<Image> getImage(String id) {
        return repository.findById(id);
    }

    public Image getImageById(String id) {
        return repository.getReferenceById(id);
    }

    public List<Image> getAllImages() {
        return repository.findAll();
    }

    public void delete(String id) {
        Image image = repository.getReferenceById(id);
        repository.delete(image);
    }
}
