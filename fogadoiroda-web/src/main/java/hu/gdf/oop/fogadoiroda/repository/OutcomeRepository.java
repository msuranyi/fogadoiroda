package hu.gdf.oop.fogadoiroda.repository;


import hu.gdf.oop.fogadoiroda.model.Outcome;

import java.util.Collection;
/**
 * Az kimenetelek tárolásáért felelős komponens interfésze.
 */
public interface OutcomeRepository extends BaseRepository<Outcome>{

    /**
     * Kimenetel azonosító alapján történő lekérdezés
     *
     * @param id a keresendő kimenetel azonosítója
     * @return a keresett kimenetel
     */
    Outcome findById(int id);

    /**
     * Eseményhez tartozó kimenetelek azonosító alapján történő lekérdezés
     *
     * @param eventId a keresendő esemény azonosítója
     * @return a keresett kimenetelek listája
     */
    Collection<Outcome> findByBetEventId(int eventId);
}
