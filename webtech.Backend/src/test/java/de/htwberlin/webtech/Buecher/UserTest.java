package de.htwberlin.webtech.Buecher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import de.htwberlin.webtech.Buecher.service.UserService;
import java.util.UUID;
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void CreateUserSaveTest() {
        User user = new User(UUID.randomUUID().toString(), "john_doe", "password123");
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
    }

    @Test
    public void DeleteUserTest() {
        String userId = UUID.randomUUID().toString();
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}

