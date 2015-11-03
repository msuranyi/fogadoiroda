package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Event;

import java.util.Collection;

public interface BetService {

    Collection<Event> findOpenEvents();

    Event findOne(Integer id);
}
