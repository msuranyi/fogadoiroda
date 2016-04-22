package hu.gdf.oop.fogadoiroda.ws.client;

import hu.gdf.oop.fogadoiroda.xml.Echo;
import hu.gdf.oop.fogadoiroda.xml.EchoResponse;
import hu.gdf.oop.fogadoiroda.xml.ObjectFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class WebServiceClient extends WebServiceGatewaySupport {

    private ObjectFactory factory = new ObjectFactory();

    public String echo(String echo) {
        Echo request = factory.createEcho();
        JAXBElement<Echo> jaxb = factory.createEcho(request);
        request.setString(echo);
        JAXBElement<EchoResponse> response = (JAXBElement<EchoResponse>) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/fogadoiroda/ws", jaxb);
        return response.getValue().getReturn();
    }

}