package dev.onesnzeroes.fuelcalculator.model;

public class FuelCalculator {

    public FuelCalculator(){

    }

    public double calculateConsumption(double fuel, double distance){
        return fuel / distance * 100;
    }
}
