package dev.onesnzeroes.fuelcalculator.controller;

import dev.onesnzeroes.fuelcalculator.db.DBCache;
import dev.onesnzeroes.fuelcalculator.model.FuelCalculator;
import dev.onesnzeroes.fuelcalculator.view.FuelCalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuelCalculatorControllerTest {

    private FuelCalculatorController controller;
    private FuelCalculator calculatorMock;
    private DBCache cacheMock;
    private FuelCalculatorUI guiMock;

    @BeforeEach
    void setUp() throws Exception {
        guiMock = mock(FuelCalculatorUI.class);
        calculatorMock = mock(FuelCalculator.class);
        cacheMock = mock(DBCache.class);

        controller = new FuelCalculatorController();

        // Inject mocks via reflection (since constructor doesn't allow DI)
        setPrivateField(controller, "calculator", calculatorMock);
        setPrivateField(controller, "cache", cacheMock);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = FuelCalculatorController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void testCalculateConsumption() {
        double fuel = 10.0;
        double distance = 100.0;
        String lang = "en_US";

        when(calculatorMock.calculateConsumption(fuel, distance)).thenReturn(5.0);

        double result = controller.calculateConsumption(fuel, distance, lang);

        assertEquals(5.0, result);

        verify(calculatorMock).calculateConsumption(fuel, distance);
        verify(cacheMock).saveResult(
                distance,
                5.0,
                null,
                fuel,
                null,
                lang
        );
    }

    @Test
    void testGetUIString() {
        when(cacheMock.getUIString("title", "en_US")).thenReturn("Fuel Calculator");

        String result = controller.getUIString("title", "en_US");

        assertEquals("Fuel Calculator", result);
        verify(cacheMock).getUIString("title", "en_US");
    }

    @Test
    void testSaveResult() {
        controller.saveResult(100.0, 5.0, 2.0, 5.0, 10.0, "en_US");

        verify(cacheMock).saveResult(
                100.0,
                5.0,
                2.0,
                5.0,
                10.0,
                "en_US"
        );
    }
}