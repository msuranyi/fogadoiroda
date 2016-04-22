package hu.gdf.oop.fogadoiroda.repository.memory;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * A felhasználók tárolását memóriában implementáló komponens.
 */
@Component
public class UserMemoryRepository extends AbstractMemoryRepository<User> implements UserRepository {

    /**
     * A komponens példányosítása és függőségeinek beinjektálásai után lefutó inicializáló metódus.
     * <br><br>
     * A konkrét esetben tesztfelhasználók létrehozása történik.
     */
    @PostConstruct
    public void init() {
        create(new User("user", "a", "USER"));
        create(new User("operator", "a", "OPERATOR"));
        create(new User("admin", "a", "OPERATOR"));
    }

    @Override
    public User findByUsername(String username) {
        return entities.get(username);
    }
}
