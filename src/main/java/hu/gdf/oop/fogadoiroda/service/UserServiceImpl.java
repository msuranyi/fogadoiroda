package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import hu.gdf.oop.fogadoiroda.web.controller.Registration;
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
}
