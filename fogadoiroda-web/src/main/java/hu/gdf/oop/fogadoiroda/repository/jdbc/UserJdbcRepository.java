package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class UserJdbcRepository extends AbstractJdbcRepository implements UserRepository {

    @Autowired
    public UserJdbcRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getSequenceName() {
        return "USERS_SEQ";
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
        int id = generateId();
        user.setId(id);
        jdbcTemplate.update("insert into USERS (ID, USERNAME, PASSWORD, AUTHORITY, ACTIVE, CREATED) values (?, ?, ?, ?, ?, ?)",
                user.getId(), user.getUsername(), user.getPassword(),"USER", !user.isLocked(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update USERS set USERNAME = ?, PASSWORD = ?, AUTHORITY = ?, ACTIVE = ? where ID = ?",user.getUsername(),user.getPassword(),user.getRoles().toString().replace("[", "").replace("]", ""),!user.isLocked(),user.getId());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("delete from USERS where ID = ?",user.getId());
    }

    private User mapRow(ResultSet rs) throws SQLException {

        User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("AUTHORITY"));

        user.setId(rs.getInt("ID"));
        user.setEmail(rs.getString("EMAIL"));
        user.setLocked(!rs.getBoolean("ACTIVE"));

        return user;
    }

}