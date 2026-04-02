package dev.onesnzeroes.fuelcalculator.db.entity;

import dev.onesnzeroes.fuelcalculator.db.entity.result.ResultEntity;
import dev.onesnzeroes.fuelcalculator.db.entity.uistring.UIStringTranslationEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "language")
public class LanguageEntity {

    /**
     * ID is iso639 language code
     */
    @Id
    @Column(name="id")
    private String id;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<UIStringTranslationEntity> translation;
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ResultEntity> result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<UIStringTranslationEntity> getTranslation() {
        return translation;
    }

    public void setTranslation(Set<UIStringTranslationEntity> translation) {
        this.translation = translation;
    }

    public Set<ResultEntity> getResult() {
        return result;
    }

    public void setResult(Set<ResultEntity> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LanguageEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
