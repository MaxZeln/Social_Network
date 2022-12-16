package ru.learnup.socialnetwork.model;

import lombok.*;

import javax.persistence.*;
//import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_friends")
@Table(name="user_friends", schema = "public")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "friend_id")
    private User friendId;

}
