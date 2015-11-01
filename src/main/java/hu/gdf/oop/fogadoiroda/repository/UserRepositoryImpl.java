package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        create(new User("user", "a", "USER"));
        create(new User("operator", "a", "USER", "OPERATOR"));

    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public void create(User user) {

        checkNotNull(user);

        String username = user.getUsername();
        checkNotExists(username);

        users.put(username, user);
    }

    @Override
    public void update(User user) {

        checkNotNull(user);

        String username = user.getUsername();
        checkExists(username);

        users.put(username, user);
    }

    @Override
    public void delete(User user) {

        checkNotNull(user);

        String username = user.getUsername();
        checkExists(username);

        users.remove(username);
    }

    private void checkNotNull(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
    }

    private void checkNotExists(String username) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User [" + username + "] already exsists");
        }
    }

    private void checkExists(String username) {
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("User [" + username + "] not found");
        }
    }
}
