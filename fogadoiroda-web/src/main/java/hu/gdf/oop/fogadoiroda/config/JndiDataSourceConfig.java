package hu.gdf.oop.fogadoiroda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

@Configuration
public class JndiDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return (new JndiDataSourceLookup()).getDataSource("jdbc/fogadoiroda_ds");
    }

}