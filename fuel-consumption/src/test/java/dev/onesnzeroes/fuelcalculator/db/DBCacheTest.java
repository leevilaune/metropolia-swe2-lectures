package dev.onesnzeroes.fuelcalculator.db;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.db.service.LanguageService;
import dev.onesnzeroes.fuelcalculator.db.service.ResultService;
import dev.onesnzeroes.fuelcalculator.db.service.UIStringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBCacheTest {

    private UIStringService uiStringService;
    private ResultService resultService;
    private LanguageService languageService;

    private DBCache cache;

    @BeforeEach
    void setUp() {
        uiStringService = mock(UIStringService.class);
        resultService = mock(ResultService.class);
        languageService = mock(LanguageService.class);

        cache = new DBCache(uiStringService, resultService, languageService);
    }

    @Test
    void testGetUIString_cacheMiss_thenHit() {
        UIStringTranslationEntity translation = mock(UIStringTranslationEntity.class);
        when(translation.getUiString()).thenReturn("Hello");

        when(uiStringService.findTranslation("greeting", "en_US"))
                .thenReturn(translation);

        // first call = DB
        String result1 = cache.getUIString("greeting", "en_US");

        // second call = cache
        String result2 = cache.getUIString("greeting", "en_US");

        assertEquals("Hello", result1);
        assertEquals("Hello", result2);

        // DB called only once
        verify(uiStringService, times(1))
                .findTranslation("greeting", "en_US");
    }

    @Test
    void testSaveResult_callsServices_andCachesLanguage() {
        LanguageEntity language = new LanguageEntity();
        language.setId("en_US");

        when(languageService.getLanguage("en_US"))
                .thenReturn(language);

        cache.saveResult(
                100.0,
                6.0,
                2.0,
                6.0,
                12.0,
                "en_US"
        );

        verify(languageService, times(1)).getLanguage("en_US");
        verify(resultService, times(1))
                .saveResult(any(ResultEntity.class));

        // second call should use cache (no DB call)
        cache.saveResult(
                100.0,
                6.0,
                2.0,
                6.0,
                12.0,
                "en_US"
        );

        verify(languageService, times(1)).getLanguage("en_US");
    }

    @Test
    void testGetUIString_missingTranslation_shouldThrow() {
        when(uiStringService.findTranslation("x", "en_US"))
                .thenThrow(new RuntimeException("not found"));

        assertThrows(RuntimeException.class, () ->
                cache.getUIString("x", "en_US")
        );
    }
}