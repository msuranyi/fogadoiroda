package hu.gdf.oop.fogadoiroda.endpoint;

import hu.gdf.oop.fogadoiroda.xml.Echo;
import hu.gdf.oop.fogadoiroda.xml.EchoResponse;
import hu.gdf.oop.fogadoiroda.xml.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class WebServiceEndpoint {

    private ObjectFactory factory = new ObjectFactory();

    @PayloadRoot(namespace = "http://xml.fogadoiroda.oop.gdf.hu/", localPart = "echo")
    @ResponsePayload
    public JAXBElement<EchoResponse> echo(@RequestPayload JAXBElement<Echo> echo) {
        EchoResponse response = factory.createEchoResponse();
        response.setReturn("Echo - " + echo.getValue().getString());
        return factory.createEchoResponse(response);
    }

}