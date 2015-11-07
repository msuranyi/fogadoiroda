package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Az események tárolását memóriában implementáló komponens.
 */
@Component
public class EventMemoryReporitory extends AbstractMemoryRepository<Event> implements EventRepository {

    /**
     * A komponens példányosítása és függőségeinek beinjektálásai után lefutó inicializáló metódus.
     * <br><br>
     * A konkrét esetben egy tesztesemény létrehozása történik.
     */
    @PostConstruct
    public void init() {

        Date start = new Date();
        Date end = new Date(start.getTime() + 1000 * 60 * 10);
        Event event = new Event("FTC - Vasas", "FTC - Vasas, NB1-es labdarúgó mérkőzés", start, end);
        Outcome outcome = new Outcome(event, "FTC nyer");
        event.getOutcomes().put(outcome.getId(), outcome);
        outcome = new Outcome(event, "Vasas nyer");
        event.getOutcomes().put(outcome.getId(), outcome);
        outcome = new Outcome(event, "Döntetlen");
        event.getOutcomes().put(outcome.getId(), outcome);
        create(event);

    }

    @Override
    public Event findById(int id) {
        return entities.get(id + "");
    }
}
