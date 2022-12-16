package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByNickname(String nickname);

    User findUserByNickname(String nickname);


}
