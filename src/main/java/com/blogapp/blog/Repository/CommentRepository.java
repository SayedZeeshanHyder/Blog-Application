package com.blogapp.blog.Repository;

import com.blogapp.blog.Entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
}
