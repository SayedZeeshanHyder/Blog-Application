package com.blogapp.blog.Controller;

import com.blogapp.blog.Config.AppConstants;
import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Payloads.PostResponse;
import com.blogapp.blog.Service.FileService;
import com.blogapp.blog.Service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    @Value("${project.image}")
    private String filePath;

    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
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
            @RequestParam(defaultValue = AppConstants.DEFAULTPAGESIZE, required = false) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULTPAGENUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULTPOSTSORTFIELD, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULTPOSTSORTORDER,required = false) String sortDir) {
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

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keywords) {
        List<PostDto> postDtos = postService.searchPost(keywords);
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable int postId, @RequestParam("image") MultipartFile file) throws IOException {
        PostDto postDto = postService.getPost(postId);
        String fileName = fileService.uploadFile(filePath,file);
        postDto.setImageName(fileName);
        postService.updatePost(postDto, postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping(value = "/{postId}/getImage",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable int postId, HttpServletResponse response) throws IOException {
        String imageName = postService.getPost(postId).getImageName();
        InputStream resource = fileService.getResource(filePath, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


}
