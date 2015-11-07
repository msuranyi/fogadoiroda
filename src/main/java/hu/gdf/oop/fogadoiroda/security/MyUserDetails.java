package hu.gdf.oop.fogadoiroda.security;

import hu.gdf.oop.fogadoiroda.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A Spring Security keretrendszerhez használt authentikációs objektum.
 */
public class MyUserDetails implements UserDetails {

    /**
     * A felhasználó loginneve.
     */
    private String username;

    /**
     * A felhasználó jelszava.
     */
    private String password;

    /**
     * A felhasználó állapota (zárolt-e vagy sem).
     */
    private boolean locked;

    /**
     * A felhasználó szerepköreinek listája.
     */
    private List<GrantedAuthority> authorities = new ArrayList<>();

    /**
     * Az authentikációs objektum konstruktora.
     *
     * @param user a konvertálandó user objektum
     */
    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.locked = user.isLocked();
        if (user.getRoles() != null) {
            for (String role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }
    }

    /**
     * A felhasználó szerepköreinek lekérdezése.
     *
     * @return A felhasználó szerepköreinek listája
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * A felhasználó jelszavának lekérdezése.
     *
     * @return a felhasználó jelszava
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * A felhasználó loginnevének lekérdezése.
     *
     * @return a felhasználó loginneve
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * A felhasználói fiók lejárt-e.
     * <br><br>
     * Jelenleg nincs használatban, minden esetben true-val tér vissza.
     *
     * @return nem lejárt fiók esetén true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * A felhasználói fiók zárolt-e.
     *
     * @return nem zárolt fiók esetén true
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * A felhasználói fiókhoz tartozó jelszó lejárt-e.
     * <br><br>
     * Jelenleg nincs használatban, minden esetben true-val tér vissza.
     *
     * @return érvényes jelszó esetén true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * A felhasználói fiók engedélyezett-e.
     * <br><br>
     * Jelenleg nincs használatban, minden esetben true-val tér vissza.
     *
     * @return engedélyezett esetén true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
