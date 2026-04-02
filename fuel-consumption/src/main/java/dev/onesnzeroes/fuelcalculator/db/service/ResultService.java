package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.db.DBConnection;
import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ResultService {

    public void saveResult(ResultEntity result){
        EntityManager em = DBConnection.getInstance().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(result);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
