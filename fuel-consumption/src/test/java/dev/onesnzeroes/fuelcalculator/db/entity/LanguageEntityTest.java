package dev.onesnzeroes.fuelcalculator.db.entity;

import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LanguageEntityTest {

    @Test
    void testGettersAndSetters() {
        LanguageEntity language = new LanguageEntity();

        language.setId("en_US");

        Set<UIStringTranslationEntity> translations = new HashSet<>();
        Set<ResultEntity> results = new HashSet<>();

        language.setTranslation(translations);
        language.setResult(results);

        assertEquals("en_US", language.getId());
        assertEquals(translations, language.getTranslation());
        assertEquals(results, language.getResult());
    }

    @Test
    void testToString() {
        LanguageEntity language = new LanguageEntity();
        language.setId("fi_FI");

        String result = language.toString();

        assertTrue(result.contains("fi_FI"));
    }

    @Test
    void testDefaultValues() {
        LanguageEntity language = new LanguageEntity();

        assertNull(language.getId());
        assertNull(language.getTranslation());
        assertNull(language.getResult());
    }

    @Test
    void testSetNullValues() {
        LanguageEntity language = new LanguageEntity();

        language.setId(null);
        language.setTranslation(null);
        language.setResult(null);

        assertNull(language.getId());
        assertNull(language.getTranslation());
        assertNull(language.getResult());
    }
}