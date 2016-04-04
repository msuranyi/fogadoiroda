package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.Outcome;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OutcomeRepository extends AbstractRepository<Outcome> {
    @Override
    protected Outcome internalFindOne(Integer id) {
        return singleResult("select * from OUTCOMES where ID=?",id);
    }

    @Override
    protected List<Outcome> internalFindAll() {
        return list("select * from OUTCOMES");
    }

    @Override
    protected void internalCreate(Outcome entity) {
        execute("insert into OUTCOMES (id, bet_event_id, title, won, sum_bet_amount) " +
                        " VALUES (?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getBetEventId(),
                entity.getTitle(),
                entity.isWon(),
                entity.getSumBetAmount());
    }

    @Override
    protected void internalUpdate(Outcome entity) {
        execute("update OUTCOMES " +
                        " set BET_EVENT_ID = ?, TITLE = ?, WON = ?, SUM_BET_AMOUNT = ? " +
                        " where ID = ?",
                entity.getBetEventId(),
                entity.getTitle(),
                entity.isWon(),
                entity.getSumBetAmount(),
                entity.getId());
    }

    @Override
    protected void internalDelete(Outcome entity) {
        execute("delete from OUTCOMES where ID = ?", entity.getId());
    }

    @Override
    protected Outcome mapRow(ResultSet resultSet) throws SQLException {

        Outcome outcome = new Outcome();

        outcome.setId(resultSet.getInt("ID"));
        outcome.setBetEventId(resultSet.getInt("BET_EVENT_ID"));
        outcome.setTitle(resultSet.getString("TITLE"));
        outcome.setWon(resultSet.getBoolean("won"));
        outcome.setSumBetAmount(resultSet.getInt("SUM_BET_AMOUNT"));

        return outcome;
    }
}
