package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import hu.gdf.oop.fogadoiroda.web.model.Registration;

import java.util.Collection;

public interface UserService {

    void register(Registration registration);

    User findByName(String username);

    Collection<User> findAll();

    void update(Profile profile);
}
