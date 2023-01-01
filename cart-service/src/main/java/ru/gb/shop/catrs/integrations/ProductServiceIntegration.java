package ru.gb.shop.catrs.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.shop.api.ProductDto;
import ru.gb.shop.api.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;


    public ProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом MS"))
                )
                .bodyToMono(ProductDto.class)
                .block();

    }

    /*
    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("api/v1/cart/clear")
                .header("username", username)
                .toBodilessEntity()
                .block();
    }

    public CartDto getUserCart(String username) {
        CArtDto cart = cartServiceWebClient.get()
                .uri("api/v1/cart")
                .header("username", username)
                //.bodyValue(body)//for POST
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(),//HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                        body -> (
                        if (body.getCode().equals(CArtServiceAppError.cartServiceErrors.CART_NOT_FOUND.name())) {
                        return new CartServiceIntegrationException("Не корректный запрос к сервису корзин");
                        }
                        if (body.getCode().equals(CArtServiceAppError.cartServiceErrors.CART_IS_BROKEN.name())) {
                        return new CartServiceIntegrationException("Не корректный запрос к сервису корзин");
                        }
                        return new CartServiceIntegrationException("Не корректный запрос к сервису корзин");
                        }
                        }
                        }
                        //.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Выполн");
                        //.onStatus(HttpStatus::is5xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Выполн");
                        .bodyToMono(CartDto.class)
                        .block();
                        return cart;
                        }
     */
}
