package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class BetServiceImpl implements BetService {

    private Map<Integer, Event> events = new HashMap<>();

    @PostConstruct
    public void init() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date start = calendar.getTime();
        calendar.add(Calendar.MINUTE, 105);
        Date end = calendar.getTime();
        Event event = new Event("FTC - Vasas", "FTC - Vasas, NB1-es labdarúgó mérkőzés", start, end);

        event.getOutcomes().add(new Outcome(event, "FTC nyer"));
        event.getOutcomes().add(new Outcome(event, "Vasas nyer"));
        event.getOutcomes().add(new Outcome(event, "Döntetlen"));

        events.put(event.getId(), event);
    }

    @Override
    public Collection<Event> findOpenEvents() {
        Date now = new Date();
        Collection<Event> result = new ArrayList<>();
        for (Event event : events.values()) {
            if (event.getStart().after(now)) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public Event findOne(Integer id) {
        return events.get(id);
    }
}
