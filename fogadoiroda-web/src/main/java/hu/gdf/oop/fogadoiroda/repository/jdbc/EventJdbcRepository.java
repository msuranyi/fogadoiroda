package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class EventJdbcRepository extends AbstractJdbcRepository implements EventRepository {

	@Autowired
	public EventJdbcRepository(DataSource dataSource) {
		super(dataSource);
	}

	@Override
    protected String getSequenceName() { return "BET_EVENTS_SEQ"; }

	@Override
	public Event findById(int id) {
		return jdbcTemplate.queryForObject("select * from BET_EVENTS where ID = ?",
			(rs, rowNum) -> mapRow(rs),
			id);
	}

	@Override
	public Collection<Event> findAll() {
		return jdbcTemplate.query("select * from BET_EVENTS",
			(rs, rowNum) -> mapRow(rs));
	}

	@Override
	public void create(Event event) {
		int id = generateId();
		event.setId(id);
        jdbcTemplate.update("insert into BET_EVENTS (ID, USER_ID, TITLE, DESCRIPTION, STATUS, START_TIME, END_TIME, CREATED) values (?, ?, ?, ?, ?, ?, ?, ?)",
                event.getId(), event.getUserId(), event.getTitle(), event.getDescription(), event.isClosed(), event.getStart(), event.getEnd(), Timestamp.valueOf(LocalDateTime.now()));
	}

	@Override
	public void update(Event event) {
        jdbcTemplate.update("update BET_EVENTS set TITLE = ?, DESCRIPTION = ?, START_TIME = ?, END_TIME = ? where ID = ?", event.getTitle(), event.getDescription(), event.getStart(), event.getEnd(), event.getId());
	}

	@Override
	public void delete(Event event) {
		jdbcTemplate.update("delete from BET_EVENTS where ID = ?", event.getId());
	}

	private Event mapRow(ResultSet rs) throws SQLException {

		Event event = new Event(rs.getInt("USER_ID"), rs.getString("TITLE"), rs.getString("DESCRIPTION"),
			rs.getTimestamp("START_TIME"), rs.getTimestamp("END_TIME"));

		event.setId(rs.getInt("ID"));

		return event;
	}
}