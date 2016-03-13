package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {

    @Override
    protected User internalFindOne(Long id) {
        return singleResult("select * from USERS where ID = ?", id);
    }

    @Override
    protected List<User> internalFindAll() {
        return list("select * from USERS");
    }

    @Override
    protected void internalCreate(User entity) {
        execute("insert into USERS (id, username, password, email, authority, active, balance, created) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getAuthority(),
                entity.isActive(),
                entity.getBalance(),
                Timestamp.valueOf(entity.getCreated()));
    }

    @Override
    protected void internalUpdate(User entity) {
        execute("update USERS " +
                " set USERNAME = ?, PASSWORD = ?, EMAIL = ?, AUTHORITY = ?, ACTIVE = ?, BALANCE = ?, CREATED = ? " +
                " where ID = ?",
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getAuthority(),
                entity.isActive(),
                entity.getBalance(),
                Timestamp.valueOf(entity.getCreated()),
                entity.getId());
    }

    @Override
    protected void internalDelete(User entity) {
        execute("delete from USERS where ID = ?", entity.getId());
    }

    @Override
    protected User mapRow(ResultSet resultSet) throws SQLException {

        User user = new User();

        user.setId(resultSet.getInt("ID"));
        user.setUsername(resultSet.getString("USERNAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setAuthority(resultSet.getString("AUTHORITY"));
        user.setActive(resultSet.getBoolean("ACTIVE"));
        user.setBalance(resultSet.getInt("BALANCE"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setCreated(resultSet.getTimestamp("CREATED").toLocalDateTime());

        return user;
    }
}
