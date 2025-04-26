package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.Product;

public class BasketItem {
    private final Product product;
    private final int amount;

    public BasketItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
