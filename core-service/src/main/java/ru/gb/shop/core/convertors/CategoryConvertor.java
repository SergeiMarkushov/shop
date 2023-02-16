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
        return CategoryDto.newBuilder()
                .withId(category.getId())
                .withTitle(category.getTitle())
                .withProducts(category.getProductEntities()
                        .stream()
                        .map(productConvertor::entityToDto)
                        .collect(Collectors.toList()))
                .build();
    }

}
