package hu.gdf.oop.fogadoiroda.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("hu.gdf.oop.fogadoiroda.config");
        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic mvcDispatcher = servletContext.addServlet("mvc", new DispatcherServlet(context));
        mvcDispatcher.setLoadOnStartup(1);
        mvcDispatcher.addMapping("/*");

        MessageDispatcherServlet mds = new MessageDispatcherServlet();
        mds.setTransformWsdlLocations(true);
        mds.setApplicationContext(context);

        ServletRegistration.Dynamic wsDispatcher = servletContext.addServlet("ws", mds);
        wsDispatcher.setLoadOnStartup(2);
        wsDispatcher.addMapping("/ws", "/ws/*");

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
        servletContext.addFilter("encodingFilter", encodingFilter);
    }

}
