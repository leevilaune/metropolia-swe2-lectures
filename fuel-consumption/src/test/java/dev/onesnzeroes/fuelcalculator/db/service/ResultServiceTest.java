package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.common.exceptions.NotFoundException;
import dev.onesnzeroes.fuelcalculator.db.DBConnection;
import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ResultServiceTest {

    private static EntityManagerFactory emf;
    private ResultService service;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("test-pu");
    }

    @AfterAll
    static void close() {
        emf.close();
    }

    @BeforeEach
    void setUp() {
        service = new ResultService();
        DBConnection.setUnit("test-pu");
    }

    @Test
    void testSaveResult_success() {
        LanguageEntity lang = new LanguageEntity();
        lang.setId("en_US");

        ResultEntity result = new ResultEntity(
                100.0,
                6.0,
                2.0,
                6.0,
                12.0,
                lang
        );

        service.saveResult(result);

        assertNotNull(result);
    }
}