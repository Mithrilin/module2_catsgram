package ru.yandex.practicum.catsgram.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    private final Set<User> users = new HashSet<>();

    @GetMapping("/users")
    public Set<User> findAll() {
        return users;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        if(users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует!");
        } else if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new InvalidEmailException("Email пользователя отсутствует!");
        } else {
            users.add(user);
        }
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new InvalidEmailException("Email пользователя отсутствует!");
        }
        users.remove(user);
        users.add(user);
        return user;
    }
}
