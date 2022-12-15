package ru.gb.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.dto.ProductDto;
import ru.gb.shop.entities.Product;
import ru.gb.shop.exceptions.ResourceNotFoundException;
import ru.gb.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProduct() {
        return productService.findAll().stream()
                .map(p -> new ProductDto(p.getId(),p.getTitle(),p.getPrice()))
                .collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable Long id) {
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()) {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден, id: " + id), HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
