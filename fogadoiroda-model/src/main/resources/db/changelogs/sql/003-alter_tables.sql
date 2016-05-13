--liquibase formatted sql

--changeset diokrisz1994@gmail.com:1
ALTER TABLE bet_events ADD description VARCHAR2(200);
ALTER TABLE bet_events ADD start_time date;
ALTER TABLE bet_events ADD end_time date;

COMMENT ON COLUMN bet_events.description IS 'Az esemény leírása.';
COMMENT ON COLUMN bet_events.start_time IS 'Az esemény kezdő időpontja.';
COMMENT ON COLUMN bet_events.end_time IS 'Az esemény záró időpontja.';
--rollback ALTER TABLE bet_events DROP COLUMN description;
--rollback ALTER TABLE bet_events DROP COLUMN start_time;
--rollback ALTER TABLE bet_events DROP COLUMN end_time;

--changeset diokrisz1994@gmail.com:2
CREATE TABLE bets 
(
	user_id NUMBER(10) NOT NULL, 
	outcome_id NUMBER(10) NOT NULL, 
	bet_event_id NUMBER(10) NOT NULL, 
	bet_amount NUMBER(10), 
	CONSTRAINT fk_bet_user FOREIGN KEY (user_id) REFERENCES users(id), 
	CONSTRAINT fk_bet_outcome FOREIGN KEY (outcome_id) REFERENCES outcomes(id), 
	CONSTRAINT fk_bet_bet_event FOREIGN KEY (bet_event_id) REFERENCES bet_events(id)
);

COMMENT ON COLUMN bets.user_id IS 'A fogadó felhasználó.';
COMMENT ON COLUMN bets.outcome_id IS 'A fogadott kimenetel.';
COMMENT ON COLUMN bets.bet_event_id IS 'A fogadott esemény.';
COMMENT ON COLUMN bets.bet_amount IS 'A fogadás összege';
--rollback drop table bets;