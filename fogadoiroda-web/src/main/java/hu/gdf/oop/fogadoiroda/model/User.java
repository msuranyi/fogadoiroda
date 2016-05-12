package hu.gdf.oop.fogadoiroda.model;

import hu.gdf.oop.fogadoiroda.web.model.Registration;

import java.util.*;

/**
 * A felhasználót reprezentáló modell osztály.
 */
public class User extends BaseEntity {

    private int id;

    private String username;

    private String password;

    private String email;

    private boolean locked;

    private List<String> roles;

    private Map<Integer, Bet> bets = new HashMap<>();

    /**
     * Felhasználó objektumot létrehozó konstruktor.
     *
     * @param username loginnév
     * @param password jelszó
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Felhasználó objektumot létrehozó konstruktor.
     *
     * A roles paraméter vararg típusú, tehát bármennyi átadható String-ként, pl:<br>
     * <code>new User("user", "pw", "USER", "OPERATOR", "ADMIN)</code>
     *
     * @param username loginnév
     * @param password jelszó
     * @param roles szerepkörök
     */
    public User(String username, String password, String... roles) {
        this(username, password);
        if (roles != null) {
            this.roles = Arrays.asList(roles);
        }
    }

    /**
     * Felhasználó objektumot létrehozó konstruktor.
     *
     * @param username loginnév
     * @param password jelszó
     * @param roles szerepkörök listája
     */
    public User(String username, String password, List<String> roles) {
        this(username, password);
        this.roles = roles;
    }

    /**
     * Felhasználó objektumot létrehozó konstruktor.
     *
     * A roles paraméter vararg típusú, tehát bármennyi átadható String-ként, pl:<br>
     * <code>new User("user", "pw", "USER", "OPERATOR", "ADMIN)</code>

     * @param registration regisztrációs modell objektum
     * @param roles szerepkörök
     */
    public User(Registration registration, String... roles) {
        this(registration.getUsername(), registration.getPassword());
        if (roles != null) {
            this.roles = Arrays.asList(roles);
        }
        this.email = registration.getEmail();
    }

    @Override
    public String getIdentifier() {
        return username;
    }

    /**
     * A felhasználó azonosítóját visszaadó metódus.
     *
     * @return a felhasználó azonosítója
     */
    public Integer getId(){ return id;}

    /**
     * A felhasználó azonosítóját
     *
     * @param id a felhasználó azonosítója
     */
    public void setId(Integer id){ this.id = id;}

    /**
     * A felhasználó loginnevét visszaadó metódus.
     *
     * @return a felhasználó loginneve
     */
    public String getUsername() {
        return username;
    }

    /**
     * A felhasználó loginnevét beállító metódus.
     *
     * @param username a felhasználó loginneve
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * A felhasználó jelszavát visszaadó metódus.
     *
     * @return a felhasználó jelszava
     */
    public String getPassword() {
        return password;
    }

    /**
     * A felhasználó jelszavát beállító metódus.
     *
     * @param password a felhasználó jelszava
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * A felhasználó szerepköreit visszaadó metódus.
     *
     * @return a felhasználó szerepkörei
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * A felhasználó szerepköreit beállító metódus.
     *
     * @param roles a felhasználó szerepkörei
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * A felhasználó email címét visszaadó metódus.
     *
     * @return a felhasználó email címe
     */
    public String getEmail() {
        return email;
    }

    /**
     * A felhasználó email címét beállító metódus.
     *
     * @param email a felhasználó email címe
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A felhasználó zároltságát visszaadó metódus.
     *
     * @return true, ha a felhasználó zárolt
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * A felhasználó zároltságát beállító metódus.
     *
     * @param locked zárolt esetén true
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * A felhasználó fogadásait visszaadó metódus.
     *
     * @return a felhasználó fogadásai
     */
    public Map<Integer, Bet> getBets() {
        return bets;
    }

    /**
     * A felhasználó fogadásait beállító metódus.
     *
     * @param bets fogadásokat tartalmazó lista
     */
    public void setBets(Collection<Bet> bets){
        bets.forEach((v)-> {
            this.bets.put(v.getEventId(), v);
        });
    }

    /**
     * Megmondja egy felhasználóról, hogy operátor-e.
     *
     * @return true, ha rendelkezik operátori szerepkörrel.
     */
    public boolean isOperator() {
        return roles.contains("OPERATOR");
    }
}
