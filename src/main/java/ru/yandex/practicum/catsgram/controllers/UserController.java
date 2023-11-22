package ru.yandex.practicum.catsgram.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> users = new HashSet<>();

    @GetMapping("/users")
    public Set<User> findAll() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        if(users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует!");
        } else if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new InvalidEmailException("Email пользователя отсутствует!");
        } else {
            log.debug("Добавлен новый пользователь");
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
