package dev.onesnzeroes.fuelcalculator.db;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.db.service.LanguageService;
import dev.onesnzeroes.fuelcalculator.db.service.ResultService;
import dev.onesnzeroes.fuelcalculator.db.service.UIStringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBCacheTest {

    private UIStringService uiStringService;
    private ResultService resultService;
    private LanguageService languageService;

    private DBCache dbCache;

    @BeforeEach
    void setUp() {
        uiStringService = mock(UIStringService.class);
        resultService = mock(ResultService.class);
        languageService = mock(LanguageService.class);

        dbCache = new DBCache(uiStringService, resultService, languageService);
    }

    // -------------------------
    // getUIString tests
    // -------------------------

    @Test
    void getUIString_shouldReturnValue_andCacheIt() {
        UIStringTranslationEntity translation = mock(UIStringTranslationEntity.class);
        when(translation.getUiString()).thenReturn("Hello");

        when(uiStringService.findTranslation("greeting", "en_US"))
                .thenReturn(translation);

        String result1 = dbCache.getUIString("greeting", "en_US");
        String result2 = dbCache.getUIString("greeting", "en_US");

        assertEquals("Hello", result1);
        assertEquals("Hello", result2);

        // service called only once (cache works)
        verify(uiStringService, times(1))
                .findTranslation("greeting", "en_US");
    }

    // -------------------------
    // saveResult tests
    // -------------------------

    @Test
    void saveResult_shouldCreateAndPersistResult() {
        LanguageEntity language = new LanguageEntity();
        language.setId("en_US");

        when(languageService.getLanguage("en_US"))
                .thenReturn(language);

        dbCache.saveResult(
                100.0,
                5.0,
                2.0,
                5.0,
                10.0,
                "en_US"
        );

        verify(languageService).getLanguage("en_US");
        verify(resultService).saveResult(any(ResultEntity.class));
    }

    @Test
    void saveResult_shouldUseCache_onSecondCall() {
        LanguageEntity language = new LanguageEntity();
        language.setId("en_US");

        when(languageService.getLanguage("en_US"))
                .thenReturn(language);

        dbCache.saveResult(100.0, 5.0, 2.0, 5.0, 10.0, "en_US");
        dbCache.saveResult(200.0, 10.0, 3.0, 10.0, 20.0, "en_US");

        // languageService only called once due to computeIfAbsent cache
        verify(languageService, times(1)).getLanguage("en_US");

        verify(resultService, times(2)).saveResult(any(ResultEntity.class));
    }
}