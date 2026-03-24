package dev.onesnzeroes.fuelcalculator.controller;

import dev.onesnzeroes.fuelcalculator.model.FuelCalculator;
import dev.onesnzeroes.fuelcalculator.view.FuelCalculatorUI;

public class FuelCalculatorController {

    private FuelCalculator calculator;
    private FuelCalculatorUI gui;

    public FuelCalculatorController(FuelCalculatorUI gui){
        this.calculator = new FuelCalculator();
        this.gui = gui;
    }

    public double calculateConsumption(double fuel, double distance){
        return this.calculator.calculateConsumption(fuel,distance);
    }
}
