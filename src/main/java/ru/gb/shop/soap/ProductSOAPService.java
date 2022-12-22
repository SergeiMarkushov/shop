package ru.gb.shop.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.entities.ProductEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSOAPService {
    private final ProductSOAPRepository productSOAPRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = pe -> {
        Product p = new Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        return p;
    };

    public List<ProductEntity> showProducts() {
        return productSOAPRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}
