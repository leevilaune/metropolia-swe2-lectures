package dev.onesnzeroes.fuelcalculator.db.entity.uistring;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UIStringEntityTest {

    @Test
    void testConstructor_setsId() {
        UIStringEntity entity = new UIStringEntity("greeting");

        assertEquals("greeting", entity.getId());
    }

    @Test
    void testSetterAndGetter_id() {
        UIStringEntity entity = new UIStringEntity();

        entity.setId("hello");

        assertEquals("hello", entity.getId());
    }

    @Test
    void testSetAndGetTranslations() {
        UIStringEntity entity = new UIStringEntity("greeting");

        UIStringTranslationEntity translation = new UIStringTranslationEntity();
        entity.setTranslations(Set.of(translation));

        assertNotNull(entity.getTranslations());
        assertEquals(1, entity.getTranslations().size());
        assertTrue(entity.getTranslations().contains(translation));
    }

    @Test
    void testDefaultConstructor() {
        UIStringEntity entity = new UIStringEntity();

        assertNull(entity.getId());
        assertNull(entity.getTranslations());
    }

    @Test
    void testToString_containsId() {
        UIStringEntity entity = new UIStringEntity("greeting");

        String result = entity.toString();

        assertTrue(result.contains("greeting"));
        assertTrue(result.contains("UIStringEntity"));
    }
}