package dev.onesnzeroes.fuelcalculator.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {

    private static EntityManagerFactory instance;

    private DBConnection(){
        instance = Persistence.createEntityManagerFactory("fuel_calculation");
    }

    public static EntityManagerFactory getInstance(){
        if(instance == null){
            instance = Persistence.createEntityManagerFactory("fuel_calculation");
        }
        return instance;
    }
}
