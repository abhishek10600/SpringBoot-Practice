package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.dto.CategoryDTO;
import com.AbhishekSharma.product.entity.Category;
import com.AbhishekSharma.product.mapper.CategoryMapper;
import com.AbhishekSharma.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    // create category
    @Override
    public CategoryDTO createCatrgory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);

        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }


    // get all categories
    // get category by id
    // delete category
}
