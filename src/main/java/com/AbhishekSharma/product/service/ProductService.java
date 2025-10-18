package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO productDTO);
    public List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(ProductDTO productDTO, Long id);
    String deleteProduct(Long id);
}
