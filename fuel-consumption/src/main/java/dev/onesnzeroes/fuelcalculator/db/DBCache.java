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

    public DBCache(){
        this.uiStringService  = new UIStringService();
        this.resultService = new ResultService();
        this.languageService = new LanguageService();
        this.uiStringCache = new HashMap<>();
        this.languageCache = new HashMap<>();
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

    public void saveResult(Double distance,Double consumption, Double price, Double totalFuel, Double totalCost, String lang){
        LanguageEntity language;
        if(this.languageCache.get(lang) == null){
            language = this.languageCache.get(lang);
        }
        language = this.languageService.getLanguage(lang);
        ResultEntity result = new ResultEntity(distance,
                consumption,
                price,
                totalFuel,
                totalCost,
                language);
        this.resultService.saveResult(result);
    }

}
