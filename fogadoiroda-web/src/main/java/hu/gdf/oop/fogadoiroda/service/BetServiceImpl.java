package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Bet;
import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.BetRepository;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import hu.gdf.oop.fogadoiroda.repository.OutcomeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * A fogadásokkal kapcsolatos üzleti logikát implementáló komponens.
 */
@Service
public class BetServiceImpl implements BetService {

    @Resource
    private UserService userService;

    @Resource
    private OutcomeRepository outcomeRepository;

    @Resource
    private BetRepository betRepository;

    @Override
    public void bet(Integer outcomeId, int betAmount) {
        User user = userService.actualUser();
        Map<Integer, Bet> bets = user.getBets();
        int eventId = outcomeRepository.findById(outcomeId).getEventId();
        if (bets.containsKey(eventId)) {
            throw new IllegalArgumentException("Erre az eseményre már adott le fogadást!");
        }

       if(outcomeRepository.findById(outcomeId) != null){
           Bet bet = new Bet(user.getId(), eventId,outcomeId, betAmount);
           bets.put(eventId, bet);
           betRepository.create(bet);
       }
    }

    @Override
    public void delete(Integer eventId) {
        User user = userService.actualUser();
        Map<Integer, Bet> bets = user.getBets();
        if (!bets.containsKey(eventId)) {
            throw new IllegalArgumentException("Erre az eseményre még nem adott le fogadást!");
        }
        betRepository.delete(bets.get(eventId));
        bets.remove(eventId);
    }
}
