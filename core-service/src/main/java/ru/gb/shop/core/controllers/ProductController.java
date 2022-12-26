package ru.gb.shop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.api.ResourceNotFoundException;
import ru.gb.shop.core.convertors.ProductConvertor;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
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
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return productConvertor.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createNewProduct(productDto);
        return productConvertor.entityToDto(product);
    }
}
