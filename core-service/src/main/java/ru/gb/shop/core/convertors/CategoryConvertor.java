package ru.gb.shop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.shop.api.CategoryDto;
import ru.gb.shop.core.entities.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConvertor {

    private final ProductConvertor productConvertor;


    public CategoryDto entityToDto(Category category) {
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProductEntities()
                .stream()
                .map(productConvertor::entityToDto)
                .collect(Collectors.toList()));
        return c;
    }

}
