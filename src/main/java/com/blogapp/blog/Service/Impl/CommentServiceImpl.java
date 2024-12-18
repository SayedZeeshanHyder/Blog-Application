package com.blogapp.blog.Service.Impl;

import com.blogapp.blog.Entities.Comments;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.CommentDto;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Repository.CommentRepository;
import com.blogapp.blog.Repository.PostRepository;
import com.blogapp.blog.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto addComment(CommentDto commentDto,int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Comments comment = modelMapper.map(commentDto, Comments.class);
        comment.setPost(post);
        Comments savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comments comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getComment(int commentId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllComments() {
        return List.of();
    }
}
