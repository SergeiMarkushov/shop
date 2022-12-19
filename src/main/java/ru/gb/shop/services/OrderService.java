package ru.gb.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final ProductService productService;
    private final ProductRepository productRepository;


    //TODO: Разкомментить.

//    public void createOrder(User user) {
//        Cart cart = new Cart();
//        Order order = new Order();
//        cart.getItems().stream().map(cartItem -> {
//            return new OrderItem(null, productService.findById(cartItem.getProductId()),
//                    order.getId(),
//                    cartItem.getQuantity(),
//                    cartItem.getPricePerProduct(),
//                    cartItem.getPrice(), );
//        });
//    }
}
