package ru.yandex.practicum.catsgram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(value = "/users")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/users/{userEmail}")
    public User getUserByEmail(@PathVariable String userEmail) {
        return userService.findUserByEmail(userEmail);
    }
}
