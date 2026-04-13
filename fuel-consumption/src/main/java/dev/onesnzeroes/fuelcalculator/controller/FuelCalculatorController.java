package dev.onesnzeroes.fuelcalculator.controller;

import dev.onesnzeroes.fuelcalculator.db.DBCache;
import dev.onesnzeroes.fuelcalculator.db.service.LanguageService;
import dev.onesnzeroes.fuelcalculator.db.service.ResultService;
import dev.onesnzeroes.fuelcalculator.db.service.UIStringService;
import dev.onesnzeroes.fuelcalculator.model.FuelCalculator;
import dev.onesnzeroes.fuelcalculator.view.FuelCalculatorUI;

public class FuelCalculatorController {

    private FuelCalculator calculator;
    private FuelCalculatorUI gui;
    private UIStringService uiStringService;
    private ResultService resultService;
    private LanguageService languageService;

    private DBCache cache;

    public FuelCalculatorController(FuelCalculatorUI gui){
        this.calculator = new FuelCalculator();
        this.gui = gui;
        this.uiStringService = new UIStringService();
        this.resultService = new ResultService();
        this.languageService = new LanguageService();
        this.cache = new DBCache(uiStringService,resultService, languageService);

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
