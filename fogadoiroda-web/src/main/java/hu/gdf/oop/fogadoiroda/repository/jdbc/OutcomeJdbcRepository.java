package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class OutcomeJdbcRepository extends AbstractJdbcRepository implements OutcomeRepository {

    @Autowired
    public OutcomeJdbcRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getSequenceName() {
        return "OUTCOMES_SEQ";
    }

    @Override
    public Outcome findById(int id) {
        return jdbcTemplate.queryForObject("select * from OUTCOMES WHERE ID = ?",
                (rs, rowNum) -> mapRow(rs),
                id);
    }

    @Override
    public List<Outcome> findByBetEventId(int eventId) {
        return jdbcTemplate.query("select * from OUTCOMES WHERE BET_EVENT_ID = ?",
                (rs, rowNum) -> mapRow(rs),
                eventId);
    }

    @Override
    public Collection<Outcome> findAll() {
        return jdbcTemplate.query("select * from OUTCOMES",
                (rs, rowNum) -> mapRow(rs));
    }

    @Override
    public void create(Outcome outcome) {
        int id = generateId();
        outcome.setId(id);
        jdbcTemplate.update("insert into OUTCOMES (ID, BET_EVENT_ID, TITLE, WON) values (?, ?, ?, ?)",
                outcome.getId(), outcome.getEventId(), outcome.getTitle(), outcome.getWon());
    }

    @Override
    public void update(Outcome outcome) {
        jdbcTemplate.update("update OUTCOMES set TITLE = ?, WON = ? where ID = ?", outcome.getTitle(), outcome.getWon(), outcome.getId());
    }

    @Override
    public void delete(Outcome outcome) {
        jdbcTemplate.update("delete from OUTCOMES where ID = ?", outcome.getId());
    }

    private Outcome mapRow(ResultSet rs) throws SQLException {

        Outcome outcome = new Outcome(rs.getInt("BET_EVENT_ID"),rs.getString("TITLE"));

        outcome.setId(rs.getInt("ID"));

        outcome.setWon(rs.getBoolean("won"));

        return outcome;
    }

}
