package ru.gb.shop.services;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.gb.shop.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Cart {
    private List<Product> products = new ArrayList<>();

    public void delete(Long id) {
        List<Product> productsForIter = new ArrayList<>(products);
        for (Product product : productsForIter) {
            if(product.getId().equals(id)) {
                products.remove(product);
            }
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProductList() {
        return products;
    }
}
