--liquibase formatted sql

--changeset marcell.suranyi@gmail.com:1
INSERT INTO users (id, username, "PASSWORD", authority, active, created)
  VALUES (users_seq.nextval, 'admin', '123', 'OPERATOR', 1, ${now});
--rollback DELETE FROM users;