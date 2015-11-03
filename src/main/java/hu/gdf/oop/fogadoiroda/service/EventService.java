package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

public interface EventService {

    Event findbyId(int id);

    void create(Event event);

    void deleteEvent(int id);

    Collection<Event> findAll();

}
