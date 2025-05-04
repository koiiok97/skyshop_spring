package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    private final UUID productId = UUID.randomUUID();
    private final UUID productId1 = UUID.randomUUID();
    private final Product testProduct = new SimpleProduct("Test Product", 10, productId);
    private final Product testProduct1= new SimpleProduct("Test Product1", 101, productId1);


    @Test
    void addProductInBasket_WhenProductNotExists_ThrowsException() {
        when(storageService.getProductById(productId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductInBasket(productId));

            verify(productBasket, never()).addProductInBasket(UUID.randomUUID());
    }

    @Test
    public void addProductInBasket_WhenProductExists_CallsAddMethod(){
        when(storageService.getProductById(productId))
                .thenReturn(Optional.of(testProduct));

        basketService.addProductInBasket(productId);

        verify(productBasket, only()).addProductInBasket(productId);
    }

    @Test
    void getUserBasket_WhenBasketEmpty_ReturnsEmptyBasket() {
        when(productBasket.getBasket())
                .thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getBasketItem().isEmpty());
        verify(storageService, never()).getProductById(UUID.randomUUID());
    }

    @Test
    void getUserBasket_WhenBasketHasItems_ReturnsValidBasket() {
        Map<UUID, Integer> basketItems = Map.of(
                productId, 1,
                productId1, 1
        );
        when(productBasket.getBasket()).thenReturn(basketItems);

        when(storageService.getProductById(productId)).thenReturn(Optional.of(testProduct));
        when(storageService.getProductById(productId1)).thenReturn(Optional.of(testProduct1));

        UserBasket result = basketService.getUserBasket();

        assertEquals(basketItems.size(), result.getBasketItem().size());

        verify(storageService, times(4)).getProductById(any());
    }
}
