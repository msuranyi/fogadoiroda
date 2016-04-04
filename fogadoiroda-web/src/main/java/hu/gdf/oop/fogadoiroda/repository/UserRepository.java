package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.User;

import java.util.Collection;

/**
 * A felhasználók tárolásáért felelős komponens interfésze.
 */
public interface UserRepository extends BaseRepository<User> {

    /**
     * Felhasználónév alapján történő keresés.
     *
     * @param username a keresendő felhasználó loginneve
     * @return a keresett felhasználó
     */
    User findByUsername(String username);
}
