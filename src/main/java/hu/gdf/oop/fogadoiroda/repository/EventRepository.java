package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

public interface EventRepository {

    void create(Event event);

    void deleteEvent(int id);

    Event findById(int id);

    Collection<Event> findAll();

}
