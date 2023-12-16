package ru.yandex.practicum.catsgram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAllPosts(
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page
    ) {
        if(!(sort.equals("asc") || sort.equals("desc"))){
            throw new IncorrectParameterException("sort");
        }
        if (page < 0) {
            throw new IncorrectParameterException("page");
        }
        if (size <= 0) {
            throw new IncorrectParameterException("size");
        }
        Integer from = page * size;
        return postService.findAll(sort, size, from);
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Post findPostById(@PathVariable int postId) {
        return postService.findById(postId);
    }
}