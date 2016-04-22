package hu.gdf.oop.fogadoiroda.repository.jdbc;

import hu.gdf.oop.fogadoiroda.config.JdbcDataSourceConfig;
import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcDataSourceConfig.class})
public class UserJdbcRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUsername("admin");
        assertNotNull(user);
        assertEquals("123", user.getPassword());
        assertEquals("OPERATOR", user.getRoles().get(0));
    }

    @Test
    public void testCreate() {
        User user = new User("alma", "1234");
        userRepository.create(user);
    }

}