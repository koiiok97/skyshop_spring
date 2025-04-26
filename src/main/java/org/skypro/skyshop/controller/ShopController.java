package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final StorageService storageService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, BasketService basketService) {
        this.storageService = storageService;
        this.basketService = basketService;
    }

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductMap();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticleMap();
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id")UUID id){
        basketService.addProductInBasket(id);
        return "Продукт успешно добавлен!";
    }


    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return  basketService.getUserBasket();
    }

}
