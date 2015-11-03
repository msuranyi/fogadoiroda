package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.Event;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class EventReporitoryImpl implements EventRepository {

    private Map<String, Event> events = new HashMap<>();


    @PostConstruct
    public void init() {
        create(new Event("Teszt", "Desc", new Date(),new Date(2016,01,01)));
    }

    @Override
    public void create(Event event) {
        int eventId = event.getId();
        events.put(eventId+"",event);
    }

    @Override
    public void deleteEvent(int id) {
        events.remove(id+"");
    }

    @Override
    public Event findById(int id) {
        return events.get(id+"");
    }

    @Override
    public Collection<Event> findAll() {
        return events.values();
    }

}
