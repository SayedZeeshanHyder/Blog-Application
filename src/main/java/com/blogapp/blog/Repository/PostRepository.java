package com.blogapp.blog.Repository;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like :key")
    List<Post> findByTitleContaining(@Param("key") String title);

}
