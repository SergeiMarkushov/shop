package ru.gb.shop.api;

import java.util.List;


public class CartDto {
    private List<CartItemDto> items;
    private double totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto(List<CartItemDto> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }
}
