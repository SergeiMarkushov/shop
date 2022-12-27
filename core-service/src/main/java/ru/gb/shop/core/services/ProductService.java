package ru.gb.shop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.api.ResourceNotFoundException;
import ru.gb.shop.core.entities.Category;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.repositories.ProductRepository;
import ru.gb.shop.core.specifications.ProductSpecifications;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    public Page<Product> find(Double minCost, Double maxCost, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessOrEqualsThan(maxCost));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
    public void deleteByIdProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
