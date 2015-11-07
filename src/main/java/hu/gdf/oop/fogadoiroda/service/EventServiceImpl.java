package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        Event iEvent = eventRepository.findById(event.getId());
        iEvent.setTitle(event.getTitle());
        iEvent.setDescription(event.getDescription());
        iEvent.setStart(new Date());
        iEvent.setEnd(event.getEnd());
    }

    @Override
    public Collection<Event> findAllOpen() {
        Date now = new Date();
        Collection<Event> events = eventRepository.findAll();
        return events.stream().filter(event -> event.getEnd().after(now)).collect(Collectors.toList());
    }
}
