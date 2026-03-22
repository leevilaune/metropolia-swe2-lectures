package dev.onesnzeroes.shoppingcart.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void shouldCalculateTotalPriceCorrectly() {
        Item item = new Item(10.0, 3);

        assertEquals(30.0, item.getTotalPrice());
    }

    @Test
    void shouldHandleZeroAmount() {
        Item item = new Item(10.0, 0);

        assertEquals(0.0, item.getTotalPrice());
    }

    @Test
    void shouldUpdatePriceCorrectly() {
        Item item = new Item(10.0, 2);

        item.setPrice(5.0);

        assertEquals(10.0, item.getTotalPrice());
    }

    @Test
    void shouldUpdateAmountCorrectly() {
        Item item = new Item(10.0, 2);

        item.setAmount(5);

        assertEquals(50.0, item.getTotalPrice());
    }
}