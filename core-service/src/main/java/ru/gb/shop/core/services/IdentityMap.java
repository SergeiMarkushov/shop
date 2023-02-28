package ru.gb.shop.core.services;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.gb.shop.core.entities.Product;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@Getter
public class IdentityMap {

    private Map<Long, Product> productMap = new HashMap<>();

    public void addProductMap(Product product) {
        if (!productMap.containsKey(product.getId())) {
            productMap.put(product.getId(), product);
        } else {
            log.info("Key already in Map");
        }
    }

    public Product getProductMap(long id) {
        Product product = productMap.get(id);
        if (product == null) {
            log.info("ID not in Map.");
        }
        return product;
    }

    public int size() {
        if (productMap == null) {
            return 0;
        }
        return productMap.size();
    }

}