package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.socialnetwork.entity.PrivateMessages;
import ru.learnup.socialnetwork.entity.User;

import java.util.List;

public interface PrivateIMessagesRepository extends JpaRepository<PrivateMessages, Long> {

    @Query("SELECT ud from privatemessages ud where ud.from=?1")
    List<PrivateMessages> findAllByFrom_id(User from_id);

    @Query("SELECT ud from privatemessages ud where ud.from=?1 and ud.too=?2")
    List<PrivateMessages> findAllByFrom_idAndToo_id(User from_id, User to_id);

}
