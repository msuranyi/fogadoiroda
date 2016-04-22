package hu.gdf.oop.fogadoiroda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@EnableWs
@ComponentScan("hu.gdf.oop.fogadoiroda.endpoint")
@Configuration
public class EndpointConfig extends WsConfigurerAdapter {

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(new SoapEnvelopeLoggingInterceptor());
    }

    @Bean(name = "fogadoiroda")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FogadoirodaService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setServiceName("FogadoirodaService");
        wsdl11Definition.setTargetNamespace("http://xml.fogadoiroda.oop.gdf.hu/");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema schema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/fogadoiroda.xsd"));
    }

}
