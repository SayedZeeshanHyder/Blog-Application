package com.blogapp.blog.Controller;

import com.blogapp.blog.Entities.Comments;
import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.CommentDto;
import com.blogapp.blog.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId) {
        CommentDto savedCommentDto = commentService.addComment(commentDto, postId);
        return ResponseEntity.ok(savedCommentDto);
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Deleted Comment Successfully",true));
    }
}
