package com.blogapp.blog.Controller;

import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {
        PostDto savedPost = postService.createPost(postDto,categoryId,userId);
        return ResponseEntity.ok(savedPost);
    }
}
