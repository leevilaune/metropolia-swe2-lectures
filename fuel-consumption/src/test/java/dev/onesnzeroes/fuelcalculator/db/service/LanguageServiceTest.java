package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.common.exceptions.NotFoundException;
import dev.onesnzeroes.fuelcalculator.db.DBConnection;
import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LanguageServiceTest {

    private static EntityManagerFactory emf;
    private LanguageService service;

    @BeforeAll
    static void setupEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("test-pu");
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    @BeforeEach
    void setUp() {
        service = new LanguageService();
        DBConnection.setUnit("test-pu");
    }

    @Test
    void testCreateAndGetLanguage() {
        LanguageEntity lang = new LanguageEntity();
        lang.setId("en_US");

        service.createLanguage(lang);

        LanguageEntity found = service.getLanguage("en_US");

        assertNotNull(found);
        assertEquals("en_US", found.getId());
    }

    @Test
    void testGetLanguage_notFound() {
        assertThrows(NotFoundException.class, () ->
                service.getLanguage("xx_XX")
        );
    }
}