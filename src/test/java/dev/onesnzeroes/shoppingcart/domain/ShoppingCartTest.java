package dev.onesnzeroes.shoppingcart.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void shouldStartWithEmptyCart() {
        ShoppingCart cart = new ShoppingCart();

        assertTrue(cart.getItems().isEmpty());
        assertEquals(0.0, cart.getTotal());
    }

    @Test
    void shouldAddItemToCart() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(10.0, 2);

        assertEquals(1, cart.getItems().size());
        assertEquals(20.0, cart.getTotal());
    }

    @Test
    void shouldCalculateTotalForMultipleItems() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(10.0, 2);
        cart.addItem(5.0, 3);

        assertEquals(35.0, cart.getTotal());
    }

    @Test
    void shouldHandleZeroQuantity() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(10.0, 0);

        assertEquals(0.0, cart.getTotal());
    }

    @Test
    void shouldHandleMultipleItemsIncludingZero() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(10.0, 2);
        cart.addItem(5.0, 0);

        assertEquals(20.0, cart.getTotal());
    }

    @Test
    void shouldSetItemsManually() {
        ShoppingCart cart = new ShoppingCart();

        List<Item> items = List.of(
                new Item(2.0, 5),
                new Item(3.0, 2)
        );

        cart.setItems(items);

        assertEquals(16.0, cart.getTotal());
        assertEquals(2, cart.getItems().size());
    }
}