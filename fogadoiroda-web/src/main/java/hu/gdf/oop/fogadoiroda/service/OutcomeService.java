package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Outcome;

import java.util.Collection;

public interface OutcomeService {
    /**
     * Azonos�t� alapj�n kimenetel keres�se.
     *
     * @param id a keresett kimenetel azonos�t�ja
     * @return a keresett kimenetel
     */
    Outcome findById(int id);

    /**
     * Kimenetel l�trehoz�sa.
     *
     * @param outcome a l�trehozand� kimenetel
     */
    void create(Outcome outcome);

    /**
     * Kimenetel m�dos�t�sa.
     *
     * @param outcome a m�dos�tand� kimenetel
     */
    void update(Outcome outcome);

    /**
     * Kimenetel t�rl�se.
     *
     * @param id a t�rlend� kimenetel azonos�t�ja
     */
    void delete(int id);

    /**
     * �sszes kimenetel lek�rdez�se.
     *
     * @return a kimenetelek list�ja
     */
    Collection<Outcome> findAll();

    /**
     * Esem�nyhez tartoz� kimenetelek lek�rdez�se.
     *
     * @param eventId az esem�ny azonos�t�ja
     *
     * @return az esem�nyhez tartoz� kimenetelek list�ja
     */
    Collection<Outcome> findByBetEventId(int eventId);
}
