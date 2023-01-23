package ru.gb.shop.catrs.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.shop.api.CartDto;
import ru.gb.shop.api.StringResponse;
import ru.gb.shop.catrs.convertors.CartConvertor;
import ru.gb.shop.catrs.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConvertor cartConvertor;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConvertor.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/quantity_change")
    public void changeQuantity(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @RequestParam Long productId, @RequestParam Integer delta) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.changeQuantity(targetUuid, productId, delta);
    }

    @GetMapping("/{uuid}/delete/{productId}")
    public void deleteFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteFromCart(targetUuid, productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.clearCart(targetUuid);
    }

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
