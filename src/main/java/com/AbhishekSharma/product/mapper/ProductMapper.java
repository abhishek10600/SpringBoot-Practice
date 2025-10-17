package com.AbhishekSharma.product.mapper;

import com.AbhishekSharma.product.dto.ProductDTO;
import com.AbhishekSharma.product.entity.Category;
import com.AbhishekSharma.product.entity.Product;

public class ProductMapper {

    //entity to dto
    public static ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }

    //dto to entity
    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setCategory(category);

        return product;
    }
}
