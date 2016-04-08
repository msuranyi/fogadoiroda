--liquibase formatted sql

--changeset marcell.suranyi@gmail.com:1
CREATE SEQUENCE users_seq INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE bet_events_seq INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE outcomes_seq INCREMENT BY 1 MINVALUE 1;
--rollback drop sequence users_seq;
--rollback drop sequence bet_events_seq;
--rollback drop sequence outcomes_seq;

--changeset marcell.suranyi@gmail.com:2
CREATE TABLE users
  (
    id NUMBER(10) NOT NULL,
    username VARCHAR2(20 char) NOT NULL,
    "PASSWORD" VARCHAR2(40 char) DEFAULT '123456' NOT NULL,
    email VARCHAR2(40 char),
    authority VARCHAR2(40 char) DEFAULT 'USER' NOT NULL,
    balance NUMBER(10) DEFAULT 0,
    active NUMBER(1) NOT NULL,
    created date NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id),
    UNIQUE (username)
  );

COMMENT ON COLUMN users.id IS 'Felhasználó elsődleges kulcsa.';
COMMENT ON COLUMN users.username IS 'Felhasználó bejelentkezési neve.';
COMMENT ON COLUMN users."PASSWORD" IS 'Felhasználó jelszava.';
COMMENT ON COLUMN users.email IS 'Felhasználó email címe.';
COMMENT ON COLUMN users.authority IS 'Felhasználó szerepköre.';
COMMENT ON COLUMN users.balance IS 'Felhasználó egyenlege.';
COMMENT ON COLUMN users.active IS 'Felhasználó aktív-e.';
COMMENT ON COLUMN users.created IS 'Felhasználó létrehozásának dátuma.';
--rollback drop table users;

--changeset marcell.suranyi@gmail.com:3
CREATE TABLE bet_events
  (
    id NUMBER(10) NOT NULL,
    user_id NUMBER(10) NOT NULL,
    title VARCHAR2(30 char) NOT NULL,
    status NUMBER(1) NOT NULL,
    created date NOT NULL,
    CONSTRAINT PK_BET_EVENTS PRIMARY KEY (id),
    CONSTRAINT fk_bet_event_user FOREIGN KEY (user_id) REFERENCES users(id)
  );

COMMENT ON COLUMN bet_events.id IS 'Esemény elsődleges kulcsa.';
COMMENT ON COLUMN bet_events.user_id IS 'Eseményt létrehozó felhasználóra hivatkozás.';
COMMENT ON COLUMN bet_events.title IS 'Esemény címe';
COMMENT ON COLUMN bet_events.status IS 'Esemény aktív-e.';
COMMENT ON COLUMN bet_events.created IS 'Esemény létrehozásának dátuma.';
--rollback drop table bet_events;

--changeset marcell.suranyi@gmail.com:4
CREATE TABLE outcomes
  (
    id NUMBER(10) NOT NULL,
    bet_event_id NUMBER(10) NOT NULL,
    title VARCHAR2(30 char) NOT NULL,
    won NUMBER(1) NOT NULL,
    sum_bet_amount NUMBER(10),
    CONSTRAINT PK_OUTCOMES PRIMARY KEY (id),
    CONSTRAINT fk_outcome_bet_event FOREIGN KEY (bet_event_id) REFERENCES bet_events(id)
  );

COMMENT ON COLUMN outcomes.id IS 'Kimenetel elsődleges kulcsa.';
COMMENT ON COLUMN outcomes.bet_event_id IS 'Kimenetel eseményére hivatkozás.';
COMMENT ON COLUMN outcomes.title IS 'Kimenetel címe.';
COMMENT ON COLUMN outcomes.won IS 'Kimenetel nyert-e.';
COMMENT ON COLUMN outcomes.sum_bet_amount IS 'Kimenetelre adott fogadások összege.';
--rollback drop table outcomes;
