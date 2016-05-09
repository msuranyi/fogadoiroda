package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.SequenceGenerator;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Az eseményekkel kapcsolatos üzleti logikát implementáló komponens.
 */
@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventRepository eventRepository;

    @Override
    public Event findbyId(int id) {
        return eventRepository.findById(id);
    }

    @Override
    public void create(Event event) {
        if (event.getId() == null) {
            event.setId(SequenceGenerator.next());
        }
        eventRepository.create(event);
    }

    @Override
    public void delete(int id) {
        Event event = eventRepository.findById(id);
        if (event.getEnd().after(new Date())) {
            eventRepository.delete(event);
        } else {
            throw new RuntimeException("Eseményt lezárás utáni törlése nem lehetséges!");
        }
    }

    @Override
    public Collection<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void update(Event event) {
        eventRepository.update(event);
    }

    @Override
    public Collection<Event> findAllOpen() {
        Date now = new Date();
        Collection<Event> events = eventRepository.findAll();
        return events.stream().filter(event -> isOpen(event, now)).collect(Collectors.toList());
    }

    private boolean isOpen(Event event, Date now) {
        boolean result = false;
        if (event.getStart() != null) {
            result = event.getEnd().after(now);
        }
        return result;
    }
}
