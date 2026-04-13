package dev.onesnzeroes.fuelcalculator.db.entity.result;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultEntityTest {

    @Test
    void testNoArgsConstructor() {
        ResultEntity result = new ResultEntity();

        assertNull(result.getId());
        assertNull(result.getDistance());
        assertNull(result.getConsumption());
        assertNull(result.getPrice());
        assertNull(result.getTotalFuel());
        assertNull(result.getTotalCost());
        assertNull(result.getLanguage());
    }

    @Test
    void testAllArgsConstructor() {
        LanguageEntity language = new LanguageEntity();
        ResultEntity result = new ResultEntity(
                100.0,
                6.5,
                1.8,
                6.5,
                11.7,
                language
        );

        assertEquals(100.0, result.getDistance());
        assertEquals(6.5, result.getConsumption());
        assertEquals(1.8, result.getPrice());
        assertEquals(6.5, result.getTotalFuel());
        assertEquals(11.7, result.getTotalCost());
        assertEquals(language, result.getLanguage());
    }

    @Test
    void testSettersAndGetters() {
        ResultEntity result = new ResultEntity();
        LanguageEntity language = new LanguageEntity();

        result.setId(1L);
        result.setDistance(200.0);
        result.setConsumption(7.2);
        result.setPrice(2.0);
        result.setTotalFuel(14.4);
        result.setTotalCost(28.8);
        result.setLanguage(language);

        assertEquals(1L, result.getId());
        assertEquals(200.0, result.getDistance());
        assertEquals(7.2, result.getConsumption());
        assertEquals(2.0, result.getPrice());
        assertEquals(14.4, result.getTotalFuel());
        assertEquals(28.8, result.getTotalCost());
        assertEquals(language, result.getLanguage());
    }

    @Test
    void testNullLanguageAllowedInSetter() {
        ResultEntity result = new ResultEntity();

        result.setLanguage(null);

        assertNull(result.getLanguage());
    }

    @Test
    void testPrecisionValues() {
        ResultEntity result = new ResultEntity();

        result.setDistance(123.456);
        result.setConsumption(5.678);
        result.setPrice(1.999);

        assertEquals(123.456, result.getDistance());
        assertEquals(5.678, result.getConsumption());
        assertEquals(1.999, result.getPrice());
    }
}