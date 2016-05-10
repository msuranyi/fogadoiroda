package hu.gdf.oop.fogadoiroda.repository;


import hu.gdf.oop.fogadoiroda.model.Outcome;

import java.util.Collection;
/**
 * Az kimenetelek t�rol�s��rt felel�s komponens interf�sze.
 */
public interface OutcomeRepository extends BaseRepository<Outcome>{

    /**
     * Kimenetel azonos�t� alapj�n t�rt�n� lek�rdez�s
     *
     * @param id a keresend� kimenetel azonos�t�ja
     * @return a keresett kimenetel
     */
    Outcome findById(int id);

    /**
     * Esem�nyhez tartoz� kimenetelek azonos�t� alapj�n t�rt�n� lek�rdez�s
     *
     * @param eventId a keresend� esem�ny azonos�t�ja
     * @return a keresett kimenetelek list�ja
     */
    Collection<Outcome> findByBetEventId(int eventId);
}
