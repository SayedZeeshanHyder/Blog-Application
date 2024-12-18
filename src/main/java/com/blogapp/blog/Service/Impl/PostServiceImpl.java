package com.blogapp.blog.Service.Impl;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.User;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Payloads.PostResponse;
import com.blogapp.blog.Repository.CategoryRepository;
import com.blogapp.blog.Repository.PostRepository;
import com.blogapp.blog.Repository.UserRepository;
import com.blogapp.blog.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        Post updatedPost = postRepository.save(post);
        PostDto updatedPostDto = modelMapper.map(updatedPost, PostDto.class);
        return updatedPostDto;
    }

    @Override
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sortingConfig = sortDir.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        PostResponse postResponse = new PostResponse();
        Pageable pageable = PageRequest.of(pageNo, pageSize,sortingConfig);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostByUserId(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("category", "id", userId));
        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return List.of();
    }
}
