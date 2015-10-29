package hu.gdf.oop.fogadoiroda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * String ApplicationContext felépítése az alkalmazás indulásakor.
 */
@Configuration
@ComponentScan({"hu.gdf.oop.fogadoiroda.service", "hu.gdf.oop.fogadoiroda.repository"})
public class SpringRootConfig {
}
