package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.Product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 100;
    public FixPriceProduct(String name, UUID id) {
        super(name, id);
    }

    @Override
    public double getPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return name + " с фиксированной ценой : Фиксированная цена " + FIX_PRICE;
    }
}
