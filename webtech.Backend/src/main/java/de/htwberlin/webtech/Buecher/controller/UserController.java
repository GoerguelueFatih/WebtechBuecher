package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @CrossOrigin
    @PostMapping("/createOrUpdate")
    public User createOrUpdateOktaUser(@RequestBody User user) {
        return userService.createOrUpdateOktaUser(
                user.getId(), user.getEmail(), user.getFirstname(), user.getLastname());
    }

    @CrossOrigin
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}