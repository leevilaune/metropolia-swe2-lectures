package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.db.exceptions.NotFoundException;
import jakarta.persistence.*;

public class UIStringService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("fuel_calculation");

    public void saveTranslation(UIStringEntity record) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(record);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public UIStringTranslationEntity findTranslation(String id, String lang) {
        try (EntityManager em = this.emf.createEntityManager()) {
            UIStringEntity entity = em.find(UIStringEntity.class, id);
            return entity.getTranslations().stream()
                    .filter(trans -> trans.getIso639Code().equalsIgnoreCase(lang))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(String.format("String %s not found for language %s",id,lang)));
        }
    }
}