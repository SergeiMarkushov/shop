package ru.gb.shop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.core.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create_order")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }
}
