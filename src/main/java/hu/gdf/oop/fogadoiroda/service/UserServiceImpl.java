package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import hu.gdf.oop.fogadoiroda.web.model.Registration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void register(Registration registration) {
        userRepository.create(new User(registration, "USER"));
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void update(Profile profile) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        user.setPassword(profile.getNewPassword());
        user.setEmail(profile.getEmail());

        userRepository.update(user);
    }
}
