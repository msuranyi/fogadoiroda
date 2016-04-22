package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class UserJdbcRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from USERS where username = ?",
                (rs, rowNum) -> mapRow(rs),
                username);
    }

    @Override
    public Collection<User> findAll() {
        return jdbcTemplate.query("select * from USERS",
                (rs, rowNum) -> mapRow(rs));
    }

    @Override
    public void create(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException();
    }

    private User mapRow(ResultSet rs) throws SQLException {

        User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"));

        user.setEmail(rs.getString("EMAIL"));
        user.setLocked(!rs.getBoolean("ACTIVE"));

        return user;
    }

}