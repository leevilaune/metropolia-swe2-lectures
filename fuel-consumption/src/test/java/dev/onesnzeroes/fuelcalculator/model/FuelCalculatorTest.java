package dev.onesnzeroes.fuelcalculator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorTest {

    private final FuelCalculator calculator = new FuelCalculator();

    @Test
    void testCalculateConsumption_normalCase() {
        double result = calculator.calculateConsumption(10.0, 100.0);

        assertEquals(10.0, result);
    }

    @Test
    void testCalculateConsumption_decimalValues() {
        double result = calculator.calculateConsumption(7.5, 150.0);

        assertEquals(5.0, result);
    }

    @Test
    void testCalculateConsumption_zeroFuel() {
        double result = calculator.calculateConsumption(0.0, 100.0);

        assertEquals(0.0, result);
    }

    @Test
    void testCalculateConsumption_largeValues() {
        double result = calculator.calculateConsumption(500.0, 1000.0);

        assertEquals(50.0, result);
    }

    @Test
    void testCalculateConsumption_divisionByZero() {
        double result = calculator.calculateConsumption(10.0, 0.0);

        assertTrue(Double.isInfinite(result));
    }

    @Test
    void testCalculateConsumption_negativeValues() {
        double result = calculator.calculateConsumption(-10.0, 100.0);

        assertEquals(-10.0, result);
    }
}