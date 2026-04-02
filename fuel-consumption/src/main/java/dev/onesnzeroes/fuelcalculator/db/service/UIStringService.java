package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.db.DBConnection;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.common.exceptions.NotFoundException;
import jakarta.persistence.*;

public class UIStringService {
    public void saveTranslation(UIStringEntity record) {
        EntityManager em = DBConnection.getInstance().createEntityManager();
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
        try (EntityManager em = DBConnection.getInstance().createEntityManager()) {
            UIStringEntity entity = em.find(UIStringEntity.class, id);
            return entity.getTranslations().stream()
                    .filter(trans -> trans.getLanguage().getId().equalsIgnoreCase(lang))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(String.format("String %s not found for language %s",id,lang)));
        }
    }
}