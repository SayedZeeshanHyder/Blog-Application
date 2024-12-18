package com.blogapp.blog.Controller;

import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Payloads.PostResponse;
import com.blogapp.blog.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {
        PostDto savedPost = postService.createPost(postDto, categoryId, userId);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) {
        List<PostDto> userPosts = postService.getPostByUserId(userId);
        return ResponseEntity.ok(userPosts);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId) {
        List<PostDto> userPosts = postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(userPosts);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId) {
        PostDto postDto = postService.getPost(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "postId", required = false) String sortBy,
            @RequestParam(defaultValue = "asc",required = false) String sortDir) {
        PostResponse postResponse = postService.getPosts(pageNo, pageSize, sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping("{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("Deleted Post with Post Id " + postId, true));
    }


}
