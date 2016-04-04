package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import hu.gdf.oop.fogadoiroda.web.model.Registration;

import java.util.Collection;

/**
 * A felhasználókkal kapcsolatos üzleti logikát leíró interfész.
 */
public interface UserService {

    /**
     * Új felhasználó regisztrálása.
     *
     * @param registration a regisztrációs modell objektum
     */
    void register(Registration registration);

    /**
     * Keresés loginnév alapján.
     *
     * @param username a keresett felhasználó loginneve
     * @return a keresett felhasználó
     */
    User findByName(String username);

    /**
     * Összes felhasználó lekérdezése.
     *
     * @return felhasználók listája
     */
    Collection<User> findAll();

    /**
     * Felhasználó módosítása.
     *
     * @param profile a módosító profil modell objektum
     */
    void update(Profile profile);

    /**
     * Felhasználó zárolása.
     *
     * @param username a zárolandó felhasználó loginneve
     */
    void lock(String username);

    /**
     * Zárolt felhasználó feloldása.
     *
     * @param username a feloldandó felhasználó loginneve
     */
    void unlock(String username);

    /**
     * Felhasználó törlése.
     *
     * @param username a törlendő felhasználó loginneve
     */
    void delete(String username);

    /**
     * Az éppen belépett felhasználó lekérdezése.
     *
     * @return az éppen belépett felhasználó
     */
    User actualUser();
}
