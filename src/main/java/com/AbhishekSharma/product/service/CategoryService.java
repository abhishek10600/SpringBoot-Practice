package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO createCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    String deleteCategory(Long id);
}
