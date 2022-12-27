package ru.gb.shop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.api.ResourceNotFoundException;
import ru.gb.shop.core.convertors.ProductConvertor;
import ru.gb.shop.core.entities.Product;
import ru.gb.shop.core.services.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConvertor productConvertor;

//    @GetMapping
//    public List<ProductDto> findAllProduct() {
//        return productService.findAll().stream()
//                .map(productConvertor::entityToDto).collect(Collectors.toList());
//    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, titlePart, page).map(
                productConvertor::entityToDto
        );
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
