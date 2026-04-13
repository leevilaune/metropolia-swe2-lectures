package dev.onesnzeroes.fuelcalculator.db;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import dev.onesnzeroes.fuelcalculator.db.service.LanguageService;
import dev.onesnzeroes.fuelcalculator.db.service.ResultService;
import dev.onesnzeroes.fuelcalculator.db.service.UIStringService;
import jakarta.persistence.Column;

import java.util.HashMap;
import java.util.Map;

public class DBCache {

    private UIStringService uiStringService;
    private ResultService resultService;
    private LanguageService languageService;
    private Map<String, UIStringTranslationEntity> uiStringCache;
    private Map<String, LanguageEntity> languageCache;

    public DBCache(UIStringService uiStringService, ResultService resultService, LanguageService languageService){
        this.uiStringService  = uiStringService;
        this.resultService = resultService;
        this.languageService = languageService;
        this.uiStringCache = new HashMap<>();
        this.languageCache = new HashMap<>();
    }

    public String getUIString(String id, String lang){
        String cacheId = id+"-"+lang;
        if (uiStringCache.containsKey(cacheId)) {
            return uiStringCache.get(cacheId).getUiString();
        }
        UIStringTranslationEntity translationEntity = this.uiStringService.findTranslation(id, lang);
        this.uiStringCache.put(cacheId, translationEntity);
        return translationEntity.getUiString();
    }

    public void saveResult(Double distance,
                           Double consumption,
                           Double price,
                           Double totalFuel,
                           Double totalCost,
                           String lang) {

        LanguageEntity language =
                this.languageCache.computeIfAbsent(lang, this.languageService::getLanguage);

        ResultEntity result = new ResultEntity(
                distance,
                consumption,
                price,
                totalFuel,
                totalCost,
                language
        );

        this.resultService.saveResult(result);
    }

}
