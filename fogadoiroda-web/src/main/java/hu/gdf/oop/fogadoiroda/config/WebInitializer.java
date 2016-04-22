package hu.gdf.oop.fogadoiroda.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * A web alkalmazás betöltő osztálya, a régi web.xml java-s megfelelője.
 * <br><br>
 * Induláskor felolvassa a Spring-es konfigurációkat a {@link SpringRootConfig} és {@link SpringSecurityConfig}
 * osztályokból, valamint a Spring MVC konfigurációt a {@link SpringWebConfig} osztályból.
 * Ez utóbbihoz bedefiniálja a Spring MVC DispatcherServlet-jét a root ("/") URL-re,
 * ami végső soron felel a kliensektől bejövő kérések továbbításáért a Controller-ek felé.
 * <br><br>
 * Ezen kívül még két Filter is felvételre került:
 * <br>
 * <ul>
 * <li>CharacterEncodingFilter - a magyar ékezetek helyes megjelenítése miatt volt szükséges</li>
 * <li>DelegatingFilterProxy - a jogosultság kezelő keretrendszernek kellett</li>
 * </ul>
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringRootConfig.class, JndiDataSourceConfig.class, SpringSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[]{
                encodingFilter,
                new DelegatingFilterProxy("springSecurityFilterChain")
        };
    }


}
