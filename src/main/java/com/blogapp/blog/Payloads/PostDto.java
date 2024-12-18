package com.blogapp.blog.Payloads;

import com.blogapp.blog.Entities.Comments;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
