package ru.learnup.socialnetwork.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 100, message = "name must be between 2 and 100 characters long")
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "Status")
    private String Status;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne(cascade = {CascadeType.ALL})
    private Image image;

    @OneToOne
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", Status='" + Status + '\'' +
                ", enabled=" + enabled +
                ", image=" + image +
                ", role=" + role +
                '}';
    }
}

