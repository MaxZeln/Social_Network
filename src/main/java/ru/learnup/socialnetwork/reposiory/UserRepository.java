package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.view.UserView;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> searchUserByLogin(String login);
    Optional<UserView> getUserByLogin(String login);

    User findUserByLogin(String login);

}
