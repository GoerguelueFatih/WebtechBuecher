package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> searchByName(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

