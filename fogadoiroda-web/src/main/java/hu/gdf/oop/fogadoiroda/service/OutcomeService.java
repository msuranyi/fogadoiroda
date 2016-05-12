package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Outcome;

import java.util.Collection;

public interface OutcomeService {
    /**
     * Azonosító alapján kimenetel keresése.
     *
     * @param id a keresett kimenetel azonosítója
     * @return a keresett kimenetel
     */
    Outcome findById(int id);

    /**
     * Kimenetel létrehozása.
     *
     * @param outcome a létrehozandó kimenetel
     */
    void create(Outcome outcome);

    /**
     * Kimenetel módosítása.
     *
     * @param outcome a módosítandó kimenetel
     */
    void update(Outcome outcome);

    /**
     * Kimenetel törlése.
     *
     * @param id a törlendõ kimenetel azonosítója
     */
    void delete(int id);

    /**
     * Összes kimenetel lekérdezése.
     *
     * @return a kimenetelek listája
     */
    Collection<Outcome> findAll();

    /**
     * Eseményhez tartozó kimenetelek lekérdezése.
     *
     * @param eventId az esemény azonosítója
     *
     * @return az eseményhez tartozó kimenetelek listája
     */
    Collection<Outcome> findByBetEventId(int eventId);
}
