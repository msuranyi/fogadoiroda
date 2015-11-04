package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class BetServiceImpl implements BetService {

    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Override
    public void bet(Integer eventId, Integer outcomeId) {
        User user = userService.actualUser();
        Map<Integer, Outcome> bets = user.getBets();
        if (bets.containsKey(eventId)) {
            throw new IllegalArgumentException("Erre az eseményre már adott le fogadást!");
        }
        Event event = eventService.findbyId(eventId);

        for (Outcome outcome : event.getOutcomes().values()) {
            if (outcome.getId().equals(outcomeId)) {
                bets.put(eventId, outcome);
                break;
            }
        }
    }

    @Override
    public void delete(Integer eventId) {
        User user = userService.actualUser();
        Map<Integer, Outcome> bets = user.getBets();
        if (!bets.containsKey(eventId)) {
            throw new IllegalArgumentException("Erre az eseményre még nem adott le fogadást!");
        }
        bets.remove(eventId);
    }

}
