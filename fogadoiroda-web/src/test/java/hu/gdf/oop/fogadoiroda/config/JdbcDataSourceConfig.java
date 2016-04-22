package hu.gdf.oop.fogadoiroda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan({"hu.gdf.oop.fogadoiroda.repository.jdbc"})
public class JdbcDataSourceConfig {

    @Value("${test.db.url}")
    private String dbUrl;

    @Value("${test.db.user}")
    private String dbUser;

    @Value("${test.db.password}")
    private String dbPassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
    }

}