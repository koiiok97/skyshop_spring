package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void addProductInBasket(UUID id){
        if(storageService.getProductById(id).isEmpty()) throw new NoSuchProductException();
        productBasket.addProductInBasket(id);
    }

    public UserBasket getUserBasket(){
        List<BasketItem> list = productBasket.getBasket().entrySet().stream()
                .map(el -> new BasketItem(storageService.getProductById(el.getKey()).get(), el.getValue()))
                .toList();
        return new UserBasket(list);
    }
}
