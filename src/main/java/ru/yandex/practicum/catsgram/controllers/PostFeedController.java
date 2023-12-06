package ru.yandex.practicum.catsgram.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@RestController
public class PostFeedController {
    @PostMapping(value = "/feed/friends")
    public List<Post> createPost(@RequestBody String post) {

        return postService.create(post);
    }
}
