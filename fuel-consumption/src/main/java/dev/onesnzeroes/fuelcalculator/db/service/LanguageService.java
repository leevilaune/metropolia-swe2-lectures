package dev.onesnzeroes.fuelcalculator.db.service;

import dev.onesnzeroes.fuelcalculator.db.DBConnection;
import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import dev.onesnzeroes.fuelcalculator.common.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LanguageService {

    public void createLanguage(LanguageEntity language){
        EntityManager em = DBConnection.getInstance().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(language);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public LanguageEntity getLanguage(String iso639){
        try(EntityManager em = DBConnection.getInstance().createEntityManager()){
            LanguageEntity lang = em.find(LanguageEntity.class, iso639);
            if(lang == null){
                throw new NotFoundException(String.format("Language %s not found", iso639));
            }
            return lang;
        }
    }

}
