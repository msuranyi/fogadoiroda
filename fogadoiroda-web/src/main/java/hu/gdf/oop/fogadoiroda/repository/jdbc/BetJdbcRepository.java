package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.Bet;
import hu.gdf.oop.fogadoiroda.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class BetJdbcRepository  extends AbstractJdbcRepository implements BetRepository{

    @Autowired
    protected BetJdbcRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getSequenceName() {
        return null;
    }

    @Override
    public Collection<Bet> findByOutcome(int outcomeId) {
        return jdbcTemplate.query("select * from BETS where OUTCOME_ID = ?",
                (rs, rowNum) -> mapRow(rs),
                outcomeId);
    }

    @Override
    public Collection<Bet> findByBetEvent(int eventId) {
        return jdbcTemplate.query("select * from BETS where BET_EVENT_ID = ?",
                (rs, rowNum) -> mapRow(rs),
                eventId);
    }

    @Override
    public Collection<Bet> findByUser(int userId) {
        return jdbcTemplate.query("select * from BETS where USER_ID = ?",
                (rs, rowNum) -> mapRow(rs),
                userId);
    }

    @Override
    public Bet find(int userId, int eventId) {
        return jdbcTemplate.queryForObject("select * from BETS where USER_ID = ? and BET_EVENT_ID = ?",
                (rs, rowNum) -> mapRow(rs),
                userId, eventId);
    }

    @Override
    public Collection<Bet> findAll() {
        return jdbcTemplate.query("select * from BETS",
                (rs, rowNum) -> mapRow(rs));
    }

    @Override
    public void create(Bet bet) {
        jdbcTemplate.update("insert into BETS (USER_ID, OUTCOME_ID, BET_EVENT_ID, BET_AMOUNT) values (?, ?, ?, ?)",
                bet.getUserId(), bet.getOutcomeId(), bet.getEventId(), bet.getBetAmount());
    }

    @Override
    public void update(Bet bet) {
        jdbcTemplate.update("update BETS set OUTCOME_ID = ? where USER_ID = ? and BET_EVENT_ID = ?", bet.getOutcomeId(), bet.getUserId(), bet.getEventId());
    }

    @Override
    public void delete(Bet bet) {
        jdbcTemplate.update("delete from BETS where USER_ID = ? and BET_EVENT_ID = ?", bet.getUserId(), bet.getEventId());
    }

    private Bet mapRow(ResultSet rs) throws SQLException {
        Bet bet = new Bet(rs.getInt("USER_ID"),rs.getInt("BET_EVENT_ID"), rs.getInt("OUTCOME_ID"), rs.getInt("BET_AMOUNT"));
        return bet;
    }
}
