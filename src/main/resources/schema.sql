create sequence hibernate_sequence start with 1000 increment by 1;

create table PERFORMANCE
(
    id              LONG PRIMARY KEY NOT NULL,
    user_profile_id LONG,
    run100m         TIME,
    run500m         TIME,
    run1000m        TIME,
    swim100m        TIME,
    swim500m        TIME,
    swim1000m       TIME,
    bike10km        TIME,
    bike25km        TIME
);

CREATE TABLE TRACK
(
    id    LONG PRIMARY KEY NOT NULL,
    name  VARCHAR(255),
    image BLOB
);

CREATE TABLE WORKOUT
(
    id              LONG PRIMARY KEY NOT NULL,
    name            VARCHAR(255),
    user_profile_id LONG,
    is_root         BOOLEAN          NOT NULL,
    duration        TIME,
    date            TIMESTAMP,
    intensive       VARCHAR(10),
    type            VARCHAR(10),
    track_id        LONG,
    parent_workout  LONG
--     foreign key (track) references TRACK (id),
--     foreign key (parent_workout) references WORKOUT (id)
);

CREATE TABLE USER_PROFILE
(
    id             LONG PRIMARY KEY NOT NULL,
    name           VARCHAR(255),
    image          BLOB,
    weight         DOUBLE,
    height         DOUBLE,
    lunx_volume    DOUBLE,
    performance_id LONG
--     foreign key (performance_id) references PERFORMANCE (id),
);
-- alter table WORKOUT
--     add foreign key (user_profile_id) references USER_PROFILE (id);

CREATE TABLE CONTEST
(
    id        LONG PRIMARY KEY NOT NULL,
    name      VARCHAR(255),
    track_id  LONG,
    date      DATE,
    winner_id LONG
--     foreign key (winner_id) references USER_PROFILE (id)
);

CREATE TABLE CONTEST_MEMBERS
(
    CONTEST_ID LONG NOT NULL,
    USER_ID    LONG NOT NULL,
    PRIMARY KEY (CONTEST_ID, USER_ID)
--     foreign key (CONTEST_ID) references CONTEST (id),
--     foreign key (USER_ID) references USER_PROFILE (id)
);