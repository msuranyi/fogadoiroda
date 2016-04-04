package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.BaseEntity;
import hu.gdf.oop.fogadoiroda.model.User;

import java.util.Collection;

/**
 * Repository műveleteket implementáló osztályok általános interfésze.
 *
 * @param <E> a tárolandó entitás típusa
 */
public interface BaseRepository<E extends BaseEntity> {

    /**
     * Az összes tárolt egyedet visszaadó metódus.
     *
     * @return az összes egyed kollekciója
     */
    Collection<E> findAll();

    /**
     * Egy új egyedet létrehozó metódus.
     *
     * @param user az új egyed
     */
    void create(E user);

    /**
     * Egy létező egyedet megváltoztató metódus.
     *
     * @param user a módosított egyed
     */
    void update(E user);

    /**
     * Egy létező egyedet eltávolító metódus.
     *
     * @param user a törlésre kijelölt egyed
     */
    void delete(E user);
}
