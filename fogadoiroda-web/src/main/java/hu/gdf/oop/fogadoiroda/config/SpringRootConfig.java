package hu.gdf.oop.fogadoiroda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * String ApplicationContext felépítése az alkalmazás indulásakor.
 * <br><br>
 * Explicit komponensek nincsekek itt deklarálva, viszont a hu.gdf.oop.fogadoiroda.service és
 * hu.gdf.oop.fogadoiroda.repository.jdbc csomagokra be van kapcsolva a komponensek szkennelése,
 * tehát minden ott található, <code>Component</code> vagy <code>Service</code> annotációval ellátott
 * osztályból létrejön egy függőségeivel teljesen felszerelt komponens.
 */
@Configuration
@ComponentScan({"hu.gdf.oop.fogadoiroda.service", "hu.gdf.oop.fogadoiroda.repository.jdbc"})
public class SpringRootConfig {
}
