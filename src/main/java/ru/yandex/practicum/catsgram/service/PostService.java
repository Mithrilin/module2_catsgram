package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.PostNotFoundException;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(String sort, Integer size, Integer from) {
        return posts.stream().sorted((post1, post2) -> {
            int compare = post1.getCreationDate().compareTo(post2.getCreationDate());
            if(sort.equals("desc")){
                compare = -1 * compare;
            }
            return compare;
        }).skip(from).limit(size).collect(Collectors.toList());
    }

    public Post create(Post post) {
        User user = userService.findUserByEmail(post.getAuthor());
        if (user == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
        posts.add(post);
        return post;
    }

    public Post findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst().orElseThrow(() -> new PostNotFoundException("Пост №" + postId + " не найден."));
    }
}