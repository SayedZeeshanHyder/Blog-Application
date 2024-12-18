package com.blogapp.blog.Service;


import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Payloads.PostDto;
import com.blogapp.blog.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,int categoryId,int userId);
    PostDto updatePost(PostDto postDto,int postId);
    void deletePost(int postId);
    PostResponse getPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPost(int postId);
    List<PostDto> getPostByUserId(int userId);
    List<PostDto> getPostByCategoryId(int categoryId);
    List<PostDto> searchPost(String keyword);

}
