package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

/**
 * Az eseményekkel kapcsolatos üzleti logikát leíró interfész.
 */
public interface EventService {

    /**
     * Azonosító alapján esemény keresése.
     *
     * @param id a keresett esemény azonosítója
     * @return a keresett esemény
     */
    Event findbyId(int id);

    /**
     * Esemény létrehozása.
     *
     * @param event a létrehozandó esemény
     */
    void create(Event event);

    /**
     * Esemény módosítása.
     *
     * @param event a módosítandó esemény
     */
    void update(Event event);

    /**
     * Esemény törlése.
     *
     * @param id a törlendő esemény azonosítója
     */
    void delete(int id);

    /**
     * Összes esemény lekérdezése.
     *
     * @return az események listája
     */
    Collection<Event> findAll();

    /**
     * Nyitott események lekérdezése.
     *
     * @return a nyitott események listája
     */
    Collection<Event> findAllOpen();
}
