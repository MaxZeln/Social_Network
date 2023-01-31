package ru.learnup.socialnetwork.model;

import lombok.*;

import javax.persistence.*;


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

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

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
                ", nickname='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Status='" + status + '\'' +
                ", enabled=" + enabled +
                ", image=" + image +
                ", role=" + role +
                '}';
    }

}

