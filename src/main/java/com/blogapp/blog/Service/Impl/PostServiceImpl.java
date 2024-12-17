package com.blogapp.blog.Service.Impl;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.User;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Repository.CategoryRepository;
import com.blogapp.blog.Repository.PostRepository;
import com.blogapp.blog.Repository.UserRepository;
import com.blogapp.blog.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto,int categoryId,int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.jpeg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        return null;
    }

    @Override
    public void deletePost(int postId) {

    }

    @Override
    public List<PostDto> getPosts() {
        return List.of();
    }

    @Override
    public PostDto getPost(int postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByUserId(int userId) {
        return List.of();
    }

    @Override
    public List<PostDto> getPostByCategoryId(int categoryId) {
        return List.of();
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return List.of();
    }
}
