package org.skypro.skyshop.service;

import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;


    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        initData();
    }

    private void initData() {
        List<Product> productList = List.of(
                new SimpleProduct("Молоко", 120, UUID.randomUUID()),
                new DiscountedProduct("Хлеб", 100, 20, UUID.randomUUID()),
                new FixPriceProduct("Сы1р11", UUID.randomUUID()),
                new DiscountedProduct("Чай", 139, 55, UUID.randomUUID()),
                new DiscountedProduct("Шоколадка", 190, 19, UUID.randomUUID()),
                new FixPriceProduct("Сы2р11", UUID.randomUUID())
        );

        List<Article> articleList = List.of(
                new Article("article1", "text1 text1 text1", UUID.randomUUID()),
                new Article("article2", "text2 text2 text2", UUID.randomUUID())
        );

        productList.forEach(el -> productMap.put(el.getId(), el));
        articleList.forEach(el -> articleMap.put(el.getId(), el));
    }

    public Collection<Product> getProductMap() {
        return productMap.values();
    }

    public Collection<Article> getArticleMap() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllElements(){
        return Stream.concat(
                productMap.values().stream(),
                articleMap.values().stream()
        ).toList();
    }

    public Optional<Product> getProductById(UUID id){
        return Optional.ofNullable(productMap.get(id));
    }
}
