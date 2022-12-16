package ru.learnup.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.dto.ImagesResponse;
import ru.learnup.socialnetwork.service.ImageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("images")
public class ImagesController {

    private final ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("image")MultipartFile image) {
        try {
            imageService.save(image);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", image.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the files: %s!", image.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<ImagesResponse> list() {
        return imageService.getAllImages()
                .stream()
                .map(this::mapToImagesResponse)
                .collect(Collectors.toList());
    }

//    @GetMapping("/image"+"/{id}")
//    public ImagesResponse Image(@PathVariable String id) {
//        Optional<Images> imageEntityOptional = imageService.getImage(id);
//        Images image = imageEntityOptional.get();
//        ImagesResponse imagesResponse = mapToImagesResponse(image);
//        return imagesResponse;
//    }

    @GetMapping("/image/{id}")
    public Optional<Image> Image(@PathVariable String id) {
        Optional<Image> imageEntityOptional = imageService.getImage(id);
        Image image = imageEntityOptional.get();
        ImagesResponse imagesResponse = mapToImagesResponse(image);
        return imageEntityOptional;
    }

    private ImagesResponse mapToImagesResponse(Image entity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(entity.getId())
                .toUriString();

        return ImagesResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contentType(entity.getContentType())
                .size(entity.getSize())
                .url(downloadURL)
                .build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<Image> imageEntityOptional = imageService.getImage(id);

        if (!imageEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Image entity = imageEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + entity.getName() + "\"")
                .contentType(MediaType.valueOf(entity.getContentType()))
                .body(entity.getBytes());
    }

    @DeleteMapping("/{imageId}")
    public void deleteFile(@PathVariable(name = "imageId") String imageId) {
        imageService.delete(imageId);
    }
}
