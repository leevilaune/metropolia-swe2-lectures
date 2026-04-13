package dev.onesnzeroes.fuelcalculator.db.entity.uistring;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UIStringTranslationEntityTest {

    @Test
    void testGettersAndSetters() {

        UIStringEntity uiString = new UIStringEntity();
        uiString.setId("greeting");

        LanguageEntity lang = new LanguageEntity();
        lang.setId("en_US");

        UIStringTranslationEntity entity =
                new UIStringTranslationEntity(uiString, lang, "Hello");

        assertEquals(uiString, entity.getUiStringId());
        assertEquals(lang, entity.getLanguage());
        assertEquals("Hello", entity.getUiString());
    }
}