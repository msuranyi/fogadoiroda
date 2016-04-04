package hu.gdf.oop.fogadoiroda.security;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * Az authentikáció számára a loginnév alapján a megfelelő felhasználó entitás kikeresését
 * végző komponens.
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {

    /**
     * A kereséshez használt UserRepository.
     */
    @Resource
    private UserRepository userRepository;

    /**
     * Loginnév alapján a megfelelő felhasználó entitást kikereső és a megfelelő konverziót
     * (a konverzió a {@link MyUserDetails#MyUserDetails(User)} konstruktorában található) elvégző metódus.
     *
     * @param username siker esetén a konvertált felhasználó objektum
     * @throws UsernameNotFoundException 0 találat esetén
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        return new MyUserDetails(user);
    }
}
