package com.blogapp.blog.Service;

import com.blogapp.blog.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto getCategory(int id);
    CategoryDto updateCategory(CategoryDto categoryDto,int id);
    void deleteCategory(int id);
    List<CategoryDto> getAllCategories();

}
