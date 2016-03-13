package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserRepositoryTest {

    private UserRepository tested = new UserRepository();

    @Test
    public void testCreate() {
        User user = new User();
        user.setId(1L);
        user.setUsername("marci");
        user.setPassword("123456");
        user.setEmail("marci@mail.hu");
        user.setAuthority("OPERATOR");
        user.setActive(true);
        user.setBalance(100);
        user.setCreated(LocalDateTime.now());
        tested.create(user);
    }

    @Test
    public void testUpdate() {
        User user = tested.findOne(1L);
        user.setPassword("12345678");
        user.setActive(false);
        tested.update(user);
    }

    @Test
    public void testFindAll() {
        List<User> list = tested.findAll();
    }

    @Test
    public void testDelete() {
        User user = tested.findOne(1L);
        tested.delete(user);
    }
}
