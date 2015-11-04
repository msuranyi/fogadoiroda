package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

public interface EventService {

    Event findbyId(int id);

    void create(Event event);

    void update(Event event);

    void delete(int id);

    Collection<Event> findAll();

}
