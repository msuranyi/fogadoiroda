package hu.gdf.oop.fogadoiroda.ws.client;

import hu.gdf.oop.fogadoiroda.xml.EchoResponse;
import hu.gdf.oop.fogadoiroda.xml.OpenEvents;
import hu.gdf.oop.fogadoiroda.xml.OpenEventsResponse;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class WebServiceClientTest {

    @Test
    public void testEcho() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("hu.gdf.oop.fogadoiroda.xml");
        WebServiceClient client = new WebServiceClient();
        client.setDefaultUri("http://localhost:8080/fogadoiroda/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        EchoResponse response = client.echo("Hello World!");
        System.out.println("generated time: " + response.getGeneratedTime());
        System.out.println("return: " + response.getReturn());
    }

    @Test
    public void testOpenEvents() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("hu.gdf.oop.fogadoiroda.xml");
        WebServiceClient client = new WebServiceClient();
        client.setDefaultUri("http://localhost:8080/fogadoiroda/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        OpenEventsResponse response = client.openEvents();
    }

}