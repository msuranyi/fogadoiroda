package hu.gdf.oop.fogadoiroda.data.repository;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRepositoryTest {

    private UserRepository tested = new UserRepository();

    @Test
    public void testCreate() {

        Integer id = createUser("marci_teszt", "123456", "marci@mail.hu", "OPERATOR", true, 100);
        User user = tested.findOne(id);

        assertEquals(id, user.getId());
        assertEquals("marci_teszt", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("marci@mail.hu", user.getEmail());
        assertEquals("OPERATOR", user.getAuthority());
        Assert.assertTrue(user.isActive());
        assertEquals(100, user.getBalance().intValue());

        // clean up
        tested.delete(user);
    }

    @Test
    public void testUpdate() {

        Integer id = createUser("janos", "123", "janos@mail.hu", "OPERATOR", true, 200);
        User user = tested.findOne(id);

        user.setPassword("12345678");
        user.setActive(false);
        tested.update(user);
        user = tested.findOne(id);

        assertEquals(id, user.getId());
        assertEquals("janos", user.getUsername());
        assertEquals("12345678", user.getPassword());
        assertEquals("janos@mail.hu", user.getEmail());
        assertEquals("OPERATOR", user.getAuthority());
        Assert.assertFalse(user.isActive());
        assertEquals(200, user.getBalance().intValue());

        // clean up
        tested.delete(user);
    }

    @Test
    public void testFindAll() {
        List<User> list = tested.findAll();
    }

    @Test
    public void testDelete() {

        Integer id = createUser("geza", "abc", "geza@mail.hu", "OPERATOR", true, 200);
        User user = tested.findOne(id);
        tested.delete(user);
        user = tested.findOne(id);

        assertNull(user);
    }

    private Integer createUser(String username, String password, String email, String authority,
                            boolean active, Integer balance) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAuthority(authority);
        user.setActive(active);
        user.setBalance(balance);
        user.setCreated(LocalDateTime.now());
        return tested.create(user);
    }

}
