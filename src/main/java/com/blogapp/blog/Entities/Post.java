package com.blogapp.blog.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @Column(name = "post_title",nullable = false,length = 100)
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

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

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
