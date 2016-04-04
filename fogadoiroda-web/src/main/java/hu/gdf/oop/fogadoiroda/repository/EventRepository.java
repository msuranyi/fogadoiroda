package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

/**
 * Az események tárolásáért felelős komponens interfésze.
 */
public interface EventRepository extends BaseRepository<Event> {

    /**
     * Esemény azonosító alapján történő lekérdezés
     *
     * @param id a keresendő esemény azonosítója
     * @return a keresett esemény
     */
    Event findById(int id);
}
