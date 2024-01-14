package de.htwberlin.webtech.Buecher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import de.htwberlin.webtech.Buecher.service.UserService;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createOrUpdateUserFromOktaTest() {
        String oktaUserId = UUID.randomUUID().toString();
        User user = new User(oktaUserId, "Test@gmail.com", "Max", "Mustermann");

        Mockito.when(userRepository.findById(oktaUserId)).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createOrUpdateOktaUser(oktaUserId, user.getEmail(), user.getFirstname(), user.getLastname());

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getFirstname(), createdUser.getFirstname());
        assertEquals(user.getLastname(), createdUser.getLastname());
    }

    @Test
    public void deleteUserTest() {
        String userId = UUID.randomUUID().toString();
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}

