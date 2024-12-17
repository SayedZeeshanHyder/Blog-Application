package com.blogapp.blog.Repository;

import com.blogapp.blog.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
