package ru.gb.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.convertors.ProductConvertor;
import ru.gb.shop.dto.ProductDto;
import ru.gb.shop.entities.ProductEntity;
import ru.gb.shop.exceptions.ResourceNotFoundException;
import ru.gb.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConvertor productConvertor;

    @GetMapping
    public List<ProductDto> findAllProduct() {
        return productService.findAll().stream()
                .map(productConvertor::entityToDto).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        ProductEntity p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return productConvertor.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        ProductEntity productEntity = productService.createNewProduct(productDto);
        return productConvertor.entityToDto(productEntity);
    }
}
