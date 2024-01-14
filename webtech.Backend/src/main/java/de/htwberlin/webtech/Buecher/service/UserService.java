package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createOrUpdateOktaUser(String oktaUserId, String email, String firstname, String lastname) {
        Optional<User> existingUser = userRepository.findById(oktaUserId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            return userRepository.save(user);
        } else {
            User newUser = new User(oktaUserId, email, firstname, lastname);
            return userRepository.save(newUser);
        }
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
