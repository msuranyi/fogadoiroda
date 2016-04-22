package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class EventJdbcRepository implements EventRepository {

    @Override
    public Event findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Event> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Event user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Event user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Event user) {
        throw new UnsupportedOperationException();
    }

}