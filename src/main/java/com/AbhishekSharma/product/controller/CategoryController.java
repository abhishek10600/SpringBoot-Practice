package com.AbhishekSharma.product.controller;

import com.AbhishekSharma.product.dto.CategoryDTO;
import com.AbhishekSharma.product.exception.CategoryAlreadyExistsException;
import com.AbhishekSharma.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD Operations",
        description = "CREATE, READ, UPDATE, and DELETE operations for Category REST API"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    //get all categories
    @Operation(
            summary = "Fetch all categories",
            description = "REST API to fetch all categories"
    )
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //create category
    @Operation(
            summary = "Create new category",
            description = "REST API to fetch all products"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // get category by id
    @Operation(
            summary = "Fetch category by id",
            description = "REST API to fetch category by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        CategoryDTO category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // delete category
    @Operation(
            summary = "Delete category",
            description = "REST API to delete a category"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

}
