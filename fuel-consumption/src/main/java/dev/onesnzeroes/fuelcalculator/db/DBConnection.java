package dev.onesnzeroes.fuelcalculator.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {

    private static EntityManagerFactory instance;
    private static String unit;

    private DBConnection(String unit){
        instance = Persistence.createEntityManagerFactory(unit);
        unit = "fuel_calculation";
    }
    public static EntityManagerFactory getInstance(){
        if(instance == null){
            instance = Persistence.createEntityManagerFactory(unit);
        }
        return instance;
    }

    public static void setUnit(String newUnit){
        unit = newUnit;
    }
}
