package org.skypro.skyshop.model.basket;


import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItem;
    private final double total;


    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = basketItem;
        this.total = basketItem.stream()
                .mapToDouble(el -> el.getProduct().getPrice() * el.getAmount())
                .sum();
    }

    public double getTotal() {
        return total;
    }

    public List<BasketItem> getBasketItem() {
        return basketItem;
    }
}
