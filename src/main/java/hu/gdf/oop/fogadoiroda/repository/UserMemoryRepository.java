package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserMemoryRepository extends AbstractMemoryRepository<User> implements UserRepository {

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
