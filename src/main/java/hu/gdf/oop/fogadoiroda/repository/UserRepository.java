package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.User;

import java.util.Collection;

public interface UserRepository {

    User findByUsername(String username);

    Collection<User> findAll();

    void create(User user);

    void update(User user);

    void delete(User user);
}
