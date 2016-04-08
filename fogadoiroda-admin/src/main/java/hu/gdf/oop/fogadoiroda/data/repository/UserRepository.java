package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import hu.gdf.oop.fogadoiroda.gui.ApplicationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {
    
    public User login(String username, String password) {
        User user = singleResult("select * from USERS where USERNAME = ? and PASSWORD = ?", username, password);
        return user;
    }

    public boolean isUnique(String username) {
        return count("select count(*) from USERS where USERNAME = ?", username) == 0;
    }
    
    @Override
    protected User internalFindOne(Integer id) {
        return singleResult("select * from USERS where ID = ?", id);
    }

    @Override
    protected List<User> internalFindAll() {
        return list("select * from USERS");
    }

    @Override
    protected String getSequenceName() {
        return "users_seq";
    }

    @Override
    protected void internalCreate(User entity) {
        if (entity.getUsername() == null || entity.getUsername().trim().isEmpty()) {
            throw new ApplicationException("A felhasználónevet kötelező megadni!");
        }
        if (!isUnique(entity.getUsername())) {
            throw new ApplicationException("A felhasználónév már foglalt!");
        }
        execute("insert into USERS (id, username, password, email, authority, active, balance, created) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getAuthority(),
                entity.isActive(),
                entity.getBalance(),
                Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    protected void internalUpdate(User entity) {
        User fetched = internalFindOne(entity.getId());
        if (fetched != null) {
            if (entity.getUsername() == null || entity.getUsername().trim().isEmpty()) {
                throw new ApplicationException("A felhasználónevet kötelező megadni!");
            }
            if (!fetched.getUsername().equals(entity.getUsername()) && !isUnique(entity.getUsername())) {
                throw new ApplicationException("A felhasználónév már foglalt!");
            }
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
        Timestamp ts = resultSet.getTimestamp("CREATED");
        user.setCreated(ts != null ? ts.toLocalDateTime() : null);

        return user;
    }
}
