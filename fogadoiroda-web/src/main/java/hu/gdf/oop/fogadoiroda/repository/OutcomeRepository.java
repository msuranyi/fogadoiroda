package hu.gdf.oop.fogadoiroda.repository;


import hu.gdf.oop.fogadoiroda.model.Outcome;

import java.util.Collection;
/**
 * Az kimenetelek tárolásáért felelõs komponens interfésze.
 */
public interface OutcomeRepository extends BaseRepository<Outcome>{

    /**
     * Kimenetel azonosító alapján történõ lekérdezés
     *
     * @param id a keresendõ kimenetel azonosítója
     * @return a keresett kimenetel
     */
    Outcome findById(int id);

    /**
     * Eseményhez tartozó kimenetelek azonosító alapján történõ lekérdezés
     *
     * @param eventId a keresendõ esemény azonosítója
     * @return a keresett kimenetelek listája
     */
    Collection<Outcome> findByBetEventId(int eventId);
}
