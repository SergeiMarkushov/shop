package ru.gb.shop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.api.ResourceNotFoundException;
import ru.gb.shop.core.entities.Category;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConvertor {

    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public Product dtoToEntity(ProductDto productDto) {
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category c = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() ->
                new ResourceNotFoundException("Категоряи не найдена"));
        p.setCategory(c);
        return p;
    }
}
