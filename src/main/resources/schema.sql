DROP TABLE IF EXISTS photo_album;
DROP SEQUENCE IF EXISTS global_seq_photo_album;

DROP TABLE IF EXISTS private_messages;
DROP SEQUENCE IF EXISTS global_seq_private_messages;

DROP TABLE IF EXISTS user_friends;
DROP SEQUENCE IF EXISTS global_seq_user_friends;

DROP TABLE IF EXISTS post;
DROP SEQUENCE IF EXISTS global_seq_post;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq_users;

DROP TABLE IF EXISTS role;
DROP SEQUENCE IF EXISTS global_seq_role;

DROP TABLE IF EXISTS images;
DROP SEQUENCE IF EXISTS global_seq_images;


CREATE SEQUENCE global_seq_images START WITH 1 INCREMENT BY 1;
CREATE TABLE images
(
    id                 BIGINT PRIMARY KEY DEFAULT nextval('global_seq_images'),
    content_type       VARCHAR(255),
    name               VARCHAR(255),
    size               BIGINT,
    bytes              BYTEA,
    original_file_name VARCHAR(255)                           NOT NULL
);

CREATE SEQUENCE global_seq_role START WITH 1 INCREMENT BY 1;
CREATE TABLE role
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('global_seq_role'),
    name VARCHAR(255)                                        NOT NULL
);

CREATE SEQUENCE global_seq_users START WITH 1 INCREMENT BY 1;
CREATE TABLE users
(
    id                 BIGINT PRIMARY KEY DEFAULT nextval('global_seq_users'),

    login              VARCHAR(255),
    email              VARCHAR(30)                            NOT NULL,
    phone              VARCHAR(12)                            NOT NULL,
    password           VARCHAR(100)                           NOT NULL,
    status             VARCHAR(255),
    enabled            BOOLEAN,
    role_id            BIGINT,
    image_id           BIGINT,

    FOREIGN KEY (role_id)
        REFERENCES role (id),

    FOREIGN KEY (image_id)
        REFERENCES images (id)
        ON DELETE CASCADE
    );
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);
CREATE UNIQUE INDEX users_unique_phone_idx ON users (phone);


CREATE SEQUENCE global_seq_user_friends START WITH 1 INCREMENT BY 1;
CREATE TABLE user_friends
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('global_seq_user_friends'),
    user_id   BIGINT                                         NOT NULL,
    friend_id BIGINT                                         NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES users (id),

    FOREIGN KEY (friend_id)
        REFERENCES users (id)

);


CREATE SEQUENCE global_seq_private_messages START WITH 1 INCREMENT BY 1;
CREATE TABLE private_messages
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('global_seq_private_messages'),
    from_id    BIGINT                                         NOT NULL,
    too_id     BIGINT                                         NOT NULL,
    content    VARCHAR(256),
    time       timestamp,

    FOREIGN KEY (from_id)
        REFERENCES users (id),

    FOREIGN KEY (too_id)
        REFERENCES users (id)

);


CREATE SEQUENCE global_seq_post START WITH 1 INCREMENT BY 1;
CREATE TABLE post
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('global_seq_post'),
    user_id   BIGINT                                         NOT NULL,
    content   VARCHAR(256),
    image_id  BIGINT,

    FOREIGN KEY (user_id)
    REFERENCES users (id),

    FOREIGN KEY (image_id)
    REFERENCES images (id)

);


CREATE SEQUENCE global_seq_photo_album START WITH 1 INCREMENT BY 1;
CREATE TABLE photo_album
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('global_seq_photo_album'),
    user_id   BIGINT                                         NOT NULL,
    image_id BIGINT                                          NOT NULL,

    FOREIGN KEY (user_id)
    REFERENCES users (id),

    FOREIGN KEY (image_id)
    REFERENCES images (id)
);




























