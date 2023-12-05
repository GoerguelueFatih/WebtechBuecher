package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String name){
        if(name != null){
            return userService.searchByName(name);
        }
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
