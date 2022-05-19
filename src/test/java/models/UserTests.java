package models;

import org.fis.cinemaregistrationapplication.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void noArgsConstructorTest() {
        User user = new User();
        Assertions.assertNotNull(user);
        Assertions.assertNull(user.getUsername());
        Assertions.assertNull(user.getRole());
        Assertions.assertNull(user.getPassword());
    }

    @Test
    public void allArgsConstructorTest() {
        User user = new User("pegu", "pegu", "Admin");
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getPassword());
        Assertions.assertNotNull(user.getUsername());
        Assertions.assertNotNull(user.getRole());
    }

    @Test
    public void hashCodeTest() {
        User user = new User("pegu", "pegu", "Admin");
        int valueUsernameHash = user.getUsername().hashCode();
        int valuePasswordHash = user.getPassword().hashCode();
        int valueAdminHash = user.getRole().hashCode();
        int result = valueUsernameHash;
        result = 31 * result + valuePasswordHash;
        result = 31 * result + valueAdminHash;
        Assertions.assertEquals(result, user.hashCode());

    }

    @Test
    public void equals() {
        User user = new User("pegu", "pegu", "Admin");
        User user2 = new User("pegu", "pegu", "Admin");
        User user3 = new User("pegu22", "pegu", "Admin");

        Assertions.assertEquals(true, user.equals(user2));
        Assertions.assertEquals(false, user2.equals(user3));
    }

    @Test
    public void getUsernameTest() {
        User user = new User("pegu", "pegu", "Admin");
        Assertions.assertEquals("pegu", user.getUsername());

    }

    @Test
    public void setUsernameTest() {
        User user = new User("pegu", "pegu", "Admin");
        user.setUsername("bogdan");
        Assertions.assertEquals("bogdan", user.getUsername());
    }

    @Test
    public void getPasswordTest() {
        User user = new User("pegu", "pegu", "Admin");
        Assertions.assertEquals("pegu", user.getPassword());

    }

    @Test
    public void setPasswordTest() {
        User user = new User("pegu", "pegu", "Admin");
        user.setPassword("pegu1");
        Assertions.assertEquals("pegu1", user.getPassword());

    }

    @Test
    public void getRole() {
        User user = new User("pegu", "pegu", "Admin");
        Assertions.assertEquals("Admin", user.getRole());
    }

    @Test
    public void setRole() {
        User user = new User("pegu", "pegu", "Admin");
        user.setRole("Client");
        Assertions.assertEquals("Client", user.getRole());
    }
}
