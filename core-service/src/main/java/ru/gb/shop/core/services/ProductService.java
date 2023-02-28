package ru.gb.shop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final IdentityMap identityMap = new IdentityMap();

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    public Page<Product> find(Integer minCost, Integer maxCost, String titlePart, Integer page) {
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
//    public Optional<Product> findProductById(Long id) {
//        return productRepository.findById(id);
//    }

    public Product findProductById(Long id) {
        Product product = this.identityMap.getProductMap(id);
        if (product != null) {
            log.info("Product found in the Map");
            return product;
        } else {
            // Try to find product in the database
            product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
            if (product != null) {
                this.identityMap.addProductMap(product);
                log.info("Product found in DB.");
                return product;
            }
        }
        return null;
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
