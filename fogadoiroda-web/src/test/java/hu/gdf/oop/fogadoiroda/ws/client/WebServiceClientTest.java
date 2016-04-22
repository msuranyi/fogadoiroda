package hu.gdf.oop.fogadoiroda.ws.client;

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
        String response = client.echo("Hello World!");
        System.out.println(response);
    }

}
