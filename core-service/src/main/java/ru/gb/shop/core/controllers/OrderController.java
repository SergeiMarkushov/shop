package ru.gb.shop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.api.OrderDto;
import ru.gb.shop.core.convertors.OrderConvertor;
import ru.gb.shop.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConvertor orderConvertor;

    @PostMapping("/create_order")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUserOrders (@RequestHeader String username) {
        return orderService.findByUsername(username).stream().map(orderConvertor::orderToDto).collect(Collectors.toList());
    }
}
