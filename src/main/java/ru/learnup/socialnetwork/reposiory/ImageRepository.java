package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
