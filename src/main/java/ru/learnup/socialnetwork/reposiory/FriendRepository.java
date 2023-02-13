package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.model.Friend;
import ru.learnup.socialnetwork.model.User;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT ud from user_friends ud where ud.userId=?1 and ud.friendId=?1")
    boolean existsByFirstUserAndSecondUser(Long first,Long second);
    @Query("SELECT ud from user_friends ud where ud.userId=?1")
    List<Friend> findByFirstUser(Long user_id);
    @Query("SELECT ud from user_friends ud where ud.friendId=?1")
    List<Friend> findBySecondUser(Long user_id);
}
