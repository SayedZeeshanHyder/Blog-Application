package com.blogapp.blog.Service;

import com.blogapp.blog.Payloads.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto,int postId);
    void deleteComment(int commentId);
    CommentDto getComment(int commentId);
    List<CommentDto> getAllComments();


}
