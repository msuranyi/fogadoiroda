package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;


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
    public void deleteEvent(int id) {
        eventRepository.deleteEvent(id);
    }

    @Override
    public Collection<Event> findAll() {
        return eventRepository.findAll();
    }
}
