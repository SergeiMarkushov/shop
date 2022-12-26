package ru.gb.shop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.shop.api.CartDto;
import ru.gb.shop.api.ResourceNotFoundException;
import ru.gb.shop.core.entities.Order;
import ru.gb.shop.core.entities.OrderItem;
import ru.gb.shop.core.entities.User;
import ru.gb.shop.core.integrations.CartServiceIntegration;
import ru.gb.shop.core.repositories.CategoryRepository;
import ru.gb.shop.core.repositories.OrderRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCart().orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).orElseThrow(() -> new UsernameNotFoundException("User not found")),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clear();

//        CartDto cartDto = new CartDto();
//        cartDto.setTotalPrice(101);
//        CartItemDto cartItemDto = new CartItemDto();
//        cartItemDto.setProductId(1L);
//        cartItemDto.setProductTitle("FJKHLSD");
//        cartItemDto.setPrice(101);
//        cartItemDto.setQuantity(1);
//        cartItemDto.setPricePerProduct(101);
//        Order order = new Order();
//        order.setUser(user);
//        order.setTotalPrice(cartDto.getTotalPrice());
//        orderRepository.save(order);
//
//    }
    }
}
