package hu.gdf.oop.fogadoiroda.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class AbstractJdbcRepository {

    protected JdbcTemplate jdbcTemplate;

    protected AbstractJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected abstract String getSequenceName();

    protected Integer generateId() {
        return jdbcTemplate.queryForObject("select " + getSequenceName() + ".nextval from DUAL", Integer.class);
    }

}