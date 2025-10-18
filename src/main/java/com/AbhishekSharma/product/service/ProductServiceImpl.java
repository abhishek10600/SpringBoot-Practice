package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.dto.ProductDTO;
import com.AbhishekSharma.product.entity.Category;
import com.AbhishekSharma.product.entity.Product;
import com.AbhishekSharma.product.mapper.ProductMapper;
import com.AbhishekSharma.product.repository.CategoryRepository;
import com.AbhishekSharma.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        // check if category exists
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Not Found"));

        // DTO -> entity
        Product product = ProductMapper.toProductEntity(productDTO, category);

        product = productRepository.save(product);

        // entity -> DTO
        return ProductMapper.toProductDTO(product);
    }

    // get all products
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // entity to DTO
        return products.stream().map(ProductMapper::toProductDTO).toList();
    }

    // get product by id
    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));

        // entity to DTO
        return ProductMapper.toProductDTO(product);
    }

    // update product
    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("Category Not Found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);

        // entity to DTO
        return ProductMapper.toProductDTO(product);
    }

    // delete product
    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully.";
    }
}
