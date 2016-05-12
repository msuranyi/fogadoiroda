package hu.gdf.oop.fogadoiroda.endpoint;

import hu.gdf.oop.fogadoiroda.service.IntegrationService;
import hu.gdf.oop.fogadoiroda.xml.Echo;
import hu.gdf.oop.fogadoiroda.xml.EchoResponse;
import hu.gdf.oop.fogadoiroda.xml.ObjectFactory;
import hu.gdf.oop.fogadoiroda.xml.OpenEvents;
import hu.gdf.oop.fogadoiroda.xml.OpenEventsResponse;
import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WebServiceEndpoint {

	private ObjectFactory factory = new ObjectFactory();

	@Resource
	private IntegrationService integrationService;

	@PayloadRoot(namespace = "http://xml.fogadoiroda.oop.gdf.hu/", localPart = "echo")
	@ResponsePayload
	public JAXBElement<EchoResponse> echo(@RequestPayload JAXBElement<Echo> echo) {

		EchoResponse response = factory.createEchoResponse();

		response.setGeneratedTime(integrationService.toXmlDate(null));
		response.setReturn("Echo - " + echo.getValue().getString());

		return factory.createEchoResponse(response);
	}

	@PayloadRoot(namespace = "http://xml.fogadoiroda.oop.gdf.hu/", localPart = "openEvents")
	@ResponsePayload
	public JAXBElement<OpenEventsResponse> openEvents(@RequestPayload JAXBElement<OpenEvents> openEvents) {

		OpenEventsResponse response = integrationService.openEvents();
		return factory.createOpenEventsResponse(response);
	}

}