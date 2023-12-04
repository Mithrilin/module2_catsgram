package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User create(User user) {
        String userEmail = user.getEmail();
        if(users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует!");
        } else if (userEmail == null || userEmail.isBlank()){
            throw new InvalidEmailException("Email пользователя отсутствует!");
        } else {
            users.put(userEmail, user);
        }
        return user;
    }

    public User update(User user) {
        String userEmail = user.getEmail();
        if (userEmail == null || userEmail.isBlank()){
            throw new InvalidEmailException("Email пользователя отсутствует!");
        }
        users.put(userEmail, user);
        return user;
    }

    public User findUserByEmail(String email) {
        if(email == null) {
            return null;
        }
        return users.get(email);
    }
}