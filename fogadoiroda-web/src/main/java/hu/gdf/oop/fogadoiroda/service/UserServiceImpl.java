package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Bet;
import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.BetRepository;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import hu.gdf.oop.fogadoiroda.repository.OutcomeRepository;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import hu.gdf.oop.fogadoiroda.web.model.Registration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * A felhasználókkal kapcsolatos üzleti logikát implementáló komponens.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BetRepository betRepository;

    @Resource
    private EventRepository eventRepository;

    @Resource
    private OutcomeRepository outcomeRepository;

    @Override
    public void register(Registration registration) {
        userRepository.create(new User(registration, "USER"));
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(Profile profile) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        if (profile.getNewPassword() != null) {
            user.setPassword(profile.getNewPassword());
        }
        user.setEmail(profile.getEmail());

        userRepository.update(user);
    }

    @Override
    public void lock(String username) {
        User user = userRepository.findByUsername(username);
        if (!user.isOperator()) {
            user.setLocked(true);
            userRepository.update(user);
        }
    }

    @Override
    public void unlock(String username) {
        User user = userRepository.findByUsername(username);
        if (!user.isOperator()) {
            user.setLocked(false);
            userRepository.update(user);
        }
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        if (user.isOperator()) {
            int operatorCount = 0;
            for (User u : userRepository.findAll()) {
                if (u.isOperator()) {
                    operatorCount++;
                }
            }
            if (operatorCount < 2) {
                throw new RuntimeException("Utolsó operátor törlése nem lehetséges!");
            }
        }
        userRepository.delete(user);
    }

    @Override
    public User actualUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findByName(username);
        Collection<Bet> bets = betRepository.findByUser(user.getId());
        bets.forEach(b -> {
            b.setUser(user);
            b.setEvent(eventRepository.findById(b.getEventId()));
            b.setOutcome(outcomeRepository.findById(b.getOutcomeId()));
        });
        user.setBets(bets);
        return user;
    }
}
