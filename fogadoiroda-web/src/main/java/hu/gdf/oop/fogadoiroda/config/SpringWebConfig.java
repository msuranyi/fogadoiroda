package hu.gdf.oop.fogadoiroda.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Spring-MVC framework felépítése az alkalmazás indulásakor.
 * <br><br>
 * Ebben a konfigurációban szerepelnek az MVC-vel kapcsolatos komponensek definíciói.
 * A konkrét komponensek mellett a hu.gdf.oop.fogadoiroda.web csomagra bekapcsolt komponens szkennelés miatt
 * a Controller annotációval ellátott osztályokból is létrejönnek új, Controller típusú komponensek.
 */
@EnableWebMvc
@Configuration
@ComponentScan("hu.gdf.oop.fogadoiroda.web")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    /**
     * Statikus resource-ok regisztrálása a Spring MVC keretrendszer számára.
     *
     * @param registry Spring MVC resource handler registry-je
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/fonts/**", "/js/**")
                .addResourceLocations("/css/", "/fonts/", "/js/");
    }

    /**
     * A felületen megjelenő címkék értékeit tartalmazó resource bundle-ok konfigurációja.
     * <br><br>
     * Ez lehetővé tenné az alkalmazás többnyelvűsítését, jelen esetben csak az eredetileg angol nyelvű
     * hibaüzenetek magyarítása miatt volt rá szükség.
     *
     * @return a message source komponens
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/errors");
        return messageSource;
    }

    /***
     * A Spring MVC keretrendszer View feloldásáért felelős komponens deklarálása.
     *
     * @return a view resolver komponens
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setOrder(1);
        viewResolver.setCache(false);
        return viewResolver;
    }

    /**
     * A Thymeleaf-es template motor testreszabása, jelen esetben az opcionális dialektus hozzáadása
     *
     * @return a template engine komponens
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(springSecurityDialect());
        return templateEngine;
    }

    /**
     * A Thymeleaf-es template-ek konfigurációját leíró komponens, például:
     * <ul>
     *     <li>hol vannak a teplate fájlok</li>
     *     <li>mi a kiterjesztésük</li>
     *     <li>mi a fájl encoding</li>
     *     <li>van-e cache-elés</li>
     * </ul>
     *
     * @return a template konfiguráció
     */
    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        return resolver;
    }

    /**
     * Thymeleaf-es opcionális dialektus, ezzel a jogosultságkezeléshez jön néhány plusz funkcionalitást
     * megvalósító frontend komponens
     *
     * @return a dialektus
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

}