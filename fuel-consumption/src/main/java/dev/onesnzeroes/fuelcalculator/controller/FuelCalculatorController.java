package dev.onesnzeroes.fuelcalculator.controller;

import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.db.service.UIStringService;
import dev.onesnzeroes.fuelcalculator.model.FuelCalculator;
import dev.onesnzeroes.fuelcalculator.view.FuelCalculatorUI;

import java.util.HashMap;
import java.util.Map;

public class FuelCalculatorController {

    private FuelCalculator calculator;
    private FuelCalculatorUI gui;

    private UIStringService uiStringService;

    private Map<String, UIStringTranslationEntity> uiStringCache;

    public FuelCalculatorController(FuelCalculatorUI gui){
        this.calculator = new FuelCalculator();
        this.gui = gui;
        this.uiStringService = new UIStringService();
        this.uiStringCache = new HashMap<>();
    }

    public double calculateConsumption(double fuel, double distance){
        return this.calculator.calculateConsumption(fuel,distance);
    }

    public String getUIString(String id, String lang){
        String cacheId = id+"-"+lang;
        if(this.uiStringCache.get(cacheId) != null){
            return this.uiStringCache.get(cacheId).getUiString();
        }
        UIStringTranslationEntity translationEntity = this.uiStringService.findTranslation(id, lang);
        this.uiStringCache.put(cacheId, translationEntity);
        return translationEntity.getUiString();
    }
}
