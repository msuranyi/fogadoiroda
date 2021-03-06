package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class BetEventRepository extends AbstractRepository<BetEvent> {

    @Override
    protected BetEvent internalFindOne(Integer id) {
        return singleResult("select * from BET_EVENTS where ID = ?", id);

    }

    @Override
    protected List<BetEvent> internalFindAll() {
        return list("select * from BET_EVENTS");
    }

    @Override
    protected String getSequenceName() {
        return "bet_events_seq";
    }

    @Override
    protected void internalCreate(BetEvent entity) {
        execute("insert into BET_EVENTS (id, user_id, title, created, status) " +
                        " VALUES (?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                Timestamp.valueOf(entity.getCreated()),
                entity.getStatus());
    }

    @Override
    protected void internalUpdate(BetEvent entity) {
        execute("update BET_EVENTS " +
                        " set USER_ID = ?, TITLE = ?, CREATED = ?, STATUS = ? " +
                        " where ID = ?",
                entity.getUserId(),
                entity.getTitle(),
                Timestamp.valueOf(entity.getCreated()),
                entity.getStatus(),
                entity.getId());
    }

    @Override
    protected void internalDelete(BetEvent entity) {
        execute("delete from BET_EVENTS where ID = ?", entity.getId());
    }

    @Override
    protected BetEvent mapRow(ResultSet resultSet) throws SQLException {

        BetEvent betEvent = new BetEvent();

        betEvent.setId(resultSet.getInt("ID"));
        betEvent.setUserId(resultSet.getInt("USER_ID"));
        betEvent.setTitle(resultSet.getString("TITLE"));
        Timestamp ts = resultSet.getTimestamp("CREATED");
        betEvent.setCreated(ts != null ? ts.toLocalDateTime() : null);
        betEvent.setStatus(resultSet.getInt("STATUS"));

        return betEvent;
    }
    
    public int countByUser(User user) {
        return count("select count(*) from BET_EVENTS where USER_ID = ?", user.getId());
    }
    
}