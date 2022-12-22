package ru.gb.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.shop.entities.Order;
import ru.gb.shop.entities.OrderItem;
import ru.gb.shop.entities.User;
import ru.gb.shop.exceptions.ResourceNotFoundException;
import ru.gb.shop.model.Cart;
import ru.gb.shop.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user) {
        Order order = new Order();
        Cart cart = cartService.getCurrentCart();

        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductEntity(productService.findById(cartItem.getProductId()).orElseThrow(() ->
                            new ResourceNotFoundException("Продукт ID = " + cartItem.getProductId() + " не найден")));
                    orderItem.setOrder(order);
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setPricePerProduct(cartItem.getPricePerProduct());
                    cartItem.setQuantity(cartItem.getQuantity());
                    orderItemService.save(orderItem);
                    return orderItem;
                }).toList();
    }
}
