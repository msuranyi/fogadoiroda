package hu.gdf.oop.fogadoiroda.repository;


import hu.gdf.oop.fogadoiroda.model.Bet;

import java.util.Collection;

public interface BetRepository extends BaseRepository<Bet>{

    Collection<Bet> findByBetEvent(int eventId);

    Collection<Bet> findByOutcome(int outcomeId);

    Collection<Bet> findByUser(int userId);

    Bet find(int userId,int eventId);

}
