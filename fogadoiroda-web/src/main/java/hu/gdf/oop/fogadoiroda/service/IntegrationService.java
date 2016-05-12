package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.xml.Event;
import hu.gdf.oop.fogadoiroda.xml.Events;
import hu.gdf.oop.fogadoiroda.xml.ObjectFactory;
import hu.gdf.oop.fogadoiroda.xml.OpenEventsResponse;
import hu.gdf.oop.fogadoiroda.xml.Outcomes;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Service;

@Service
public class IntegrationService {

	private ObjectFactory factory = new ObjectFactory();

	@Resource
	private EventService eventService;

	@Resource
	private OutcomeService outcomeService;

	public XMLGregorianCalendar toXmlDate(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		if (date != null) {
			gc.setTime(date);
		}

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException("Nem várt hiba történt.");
		}
	}

	public OpenEventsResponse openEvents() {

		OpenEventsResponse response = factory.createOpenEventsResponse();

		response.setGeneratedTime(toXmlDate(null));
		Events events = factory.createEvents();
		eventService.findAllOpen().forEach(e -> {
			Event event = factory.createEvent();
			event.setId(e.getId());
			event.setTitle(e.getTitle());
			event.setDescription(e.getDescription());
			event.setStart(toXmlDate(e.getStart()));
			event.setEnd(toXmlDate(e.getEnd()));
			Outcomes outcomes = factory.createOutcomes();

			Collection<Outcome> eventOutcomes = outcomeService.findByBetEventId(
				event.getId());
			eventOutcomes.forEach((v) -> {
				hu.gdf.oop.fogadoiroda.xml.Outcome outcome = factory.createOutcome();
				outcome.setId(v.getId());
				outcome.setTitle(v.getTitle());
				outcomes.getOutcome().add(outcome);
			});
			event.setOutcomes(outcomes);
			events.getEvent().add(event);
		});
		response.setEvents(events);

		return response;
	}

}