package ru.gb.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.dto.ProductDto;
import ru.gb.shop.entities.Category;
import ru.gb.shop.entities.ProductEntity;
import ru.gb.shop.exceptions.ResourceNotFoundException;
import ru.gb.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity createNewProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(productDto.getPrice());
        productEntity.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        productEntity.setCategory(category);
        productRepository.save(productEntity);
        return productEntity;
    }
}
