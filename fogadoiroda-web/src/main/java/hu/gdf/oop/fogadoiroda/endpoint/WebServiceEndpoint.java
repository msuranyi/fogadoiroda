package hu.gdf.oop.fogadoiroda.endpoint;

import hu.gdf.oop.fogadoiroda.service.EventService;
import hu.gdf.oop.fogadoiroda.service.OutcomeService;
import hu.gdf.oop.fogadoiroda.xml.Echo;
import hu.gdf.oop.fogadoiroda.xml.EchoResponse;
import hu.gdf.oop.fogadoiroda.xml.Event;
import hu.gdf.oop.fogadoiroda.xml.Events;
import hu.gdf.oop.fogadoiroda.xml.ObjectFactory;
import hu.gdf.oop.fogadoiroda.xml.OpenEvents;
import hu.gdf.oop.fogadoiroda.xml.OpenEventsResponse;
import hu.gdf.oop.fogadoiroda.xml.Outcome;
import hu.gdf.oop.fogadoiroda.xml.Outcomes;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WebServiceEndpoint {

	private ObjectFactory factory = new ObjectFactory();

	@Resource
	private EventService eventService;

	@Resource
	private OutcomeService outcomeService;

	@PayloadRoot(namespace = "http://xml.fogadoiroda.oop.gdf.hu/", localPart = "echo")
	@ResponsePayload
	public JAXBElement<EchoResponse> echo(@RequestPayload JAXBElement<Echo> echo) {

		EchoResponse response = factory.createEchoResponse();

		response.setGeneratedTime(toXmlDate(null));
		response.setReturn("Echo - " + echo.getValue().getString());

		return factory.createEchoResponse(response);
	}

	@PayloadRoot(namespace = "http://xml.fogadoiroda.oop.gdf.hu/", localPart = "openEvents")
	@ResponsePayload
	public JAXBElement<OpenEventsResponse> openEvents(@RequestPayload JAXBElement<OpenEvents> openEvents) {

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

			Collection<hu.gdf.oop.fogadoiroda.model.Outcome> eventOutcomes = outcomeService.findByBetEventId(
				event.getId());
			eventOutcomes.forEach((v) -> {
				Outcome outcome = factory.createOutcome();
				outcome.setId(v.getId());
				outcome.setTitle(v.getTitle());
				outcomes.getOutcome().add(outcome);
			});
			event.setOutcomes(outcomes);
			events.getEvent().add(event);
		});
		response.setEvents(events);

		return factory.createOpenEventsResponse(response);
	}

	private XMLGregorianCalendar toXmlDate(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		if (date != null) {
			gc.setTime(date);
//		} else {
//			zdt = LocalDateTime.now().atZone(ZoneId.systemDefault());
		}

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException("Nem várt hiba történt.");
		}
	}

}