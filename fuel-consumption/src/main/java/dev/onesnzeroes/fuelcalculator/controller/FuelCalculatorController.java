package dev.onesnzeroes.fuelcalculator.controller;

import dev.onesnzeroes.fuelcalculator.db.DBCache;
import dev.onesnzeroes.fuelcalculator.model.FuelCalculator;
import dev.onesnzeroes.fuelcalculator.view.FuelCalculatorUI;

public class FuelCalculatorController {

    private FuelCalculator calculator;
    private FuelCalculatorUI gui;

    private DBCache cache;

    public FuelCalculatorController(FuelCalculatorUI gui){
        this.calculator = new FuelCalculator();
        this.gui = gui;
        this.cache = new DBCache();

    }

    public double calculateConsumption(double fuel, double distance, String lang) {
        double consumption = this.calculator.calculateConsumption(fuel,distance);
        this.saveResult(distance,
                consumption,
                null,
                fuel,
                null,
                lang);
        return consumption;
    }

    public String getUIString(String id, String lang){
        return this.cache.getUIString(id, lang);
    }
    public void saveResult(Double distance,Double consumption, Double price, Double totalFuel, Double totalCost, String lang){
        this.cache.saveResult(distance,
                consumption,
                price,
                totalFuel,
                totalCost,
                lang);
    }
}
