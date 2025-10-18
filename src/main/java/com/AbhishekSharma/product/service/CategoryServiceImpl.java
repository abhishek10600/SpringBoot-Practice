package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.dto.CategoryDTO;
import com.AbhishekSharma.product.entity.Category;
import com.AbhishekSharma.product.exception.CategoryAlreadyExistsException;
import com.AbhishekSharma.product.mapper.CategoryMapper;
import com.AbhishekSharma.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    // create category
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category " + categoryDTO.getName() + " already exists!");
        }

        Category category = CategoryMapper.toCategoryEntity(categoryDTO);

        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    // get all categories
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    // get category by id
    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not found"));

        //entity -> DTO
        return CategoryMapper.toCategoryDTO(category);
    }

    // delete category
    @Override
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category successfully deleted";
    }
}
