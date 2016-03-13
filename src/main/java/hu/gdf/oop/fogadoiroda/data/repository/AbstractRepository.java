package hu.gdf.oop.fogadoiroda.data.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<E> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Connection conn = null;

    private String driver = "oracle.jdbc.driver.OracleDriver";

    private String url = "jdbc:oracle:thin:@szofidb:1521:szofidb";

    private String user = "fogadoiroda";

    private String password = "fogadoiroda";

    private void createConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Adatbázis driver nem található! ", ex);
        } catch (SQLException ex) {
            LOGGER.error("Hiba történt a kapcsolat felépítése közben! ", ex);
        }
    }

    private void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                LOGGER.error("Hiba történt a kapcsolat bezárása közben! ", ex);
            }
        }
    }

    public E findOne(Long id) {
        createConnection();
        E result = internalFindOne(id);
        closeConnection();
        return result;
    }

    public List<E> findAll() {
        createConnection();
        List<E> result = internalFindAll();
        closeConnection();
        return result;
    }

    public void create(E entity) {
        createConnection();
        internalCreate(entity);
        closeConnection();
    }

    public void update(E entity) {
        createConnection();
        internalUpdate(entity);
        closeConnection();
    }

    public void delete(E entity) {
        createConnection();
        internalDelete(entity);
        closeConnection();
    }

    protected abstract E internalFindOne(Long id);

    protected abstract List<E> internalFindAll();

    protected abstract void internalCreate(E entity);

    protected abstract void internalUpdate(E entity);

    protected abstract void internalDelete(E entity);

    protected abstract E mapRow(ResultSet resultSet) throws SQLException;

    protected void execute(String statement, Object... params) {
        try (PreparedStatement stmt = conn.prepareStatement(statement)) {
            if (params != null) {
                int index = 1;
                for (Object param : params) {
                    stmt.setObject(index++, param);
                }
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Hiba történt az utasítás végrehajtása közben! ", ex);
        }
    }

    protected E singleResult(String sql, Object... params) {
        List<E> results = query(sql, params);
        if (results.size() > 1) {
            throw new RuntimeException("Egynél több eredményt adott a lekérdezés!");
        }
        return results.size() > 0 ? results.get(0) : null;
    }

    protected List<E> list(String sql, Object... params) {
        List<E> results = query(sql, params);
        return results;
    }

    private List<E> query(String sql, Object... params) {

        List<E> results = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            if (params != null) {
                int index = 1;
                for (Object param : params) {
                    statement.setObject(index++, param);
                }
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(mapRow(resultSet));
            }
        } catch (SQLException ex) {
            LOGGER.error("Hiba történt a lekérdezés közben! ", ex);
        }

        return results;
    }

}