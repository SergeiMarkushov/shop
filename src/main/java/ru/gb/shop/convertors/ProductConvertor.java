package ru.gb.shop.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.dto.ProductDto;
import ru.gb.shop.entities.Category;
import ru.gb.shop.entities.ProductEntity;
import ru.gb.shop.exceptions.ResourceNotFoundException;
import ru.gb.shop.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConvertor {

    private final CategoryService categoryService;

    public ProductDto entityToDto(ProductEntity productEntity) {
        return new ProductDto(productEntity.getId(), productEntity.getTitle(), productEntity.getPrice(), productEntity.getCategory().getTitle());
    }

    public ProductEntity dtoToEntity(ProductDto productDto) {
        ProductEntity p = new ProductEntity();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category c = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() ->
                new ResourceNotFoundException("Категоряи не найдена"));
        p.setCategory(c);
        return p;
    }
}
