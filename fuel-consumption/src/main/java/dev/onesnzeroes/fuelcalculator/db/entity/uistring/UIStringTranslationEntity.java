package dev.onesnzeroes.fuelcalculator.db.entity.uistring;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "uistring_translation")
public class UIStringTranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uiStringId", nullable = false)
    private UIStringEntity uiStringId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language", nullable = false)
    private LanguageEntity language;
    @Column(name="string")
    private String uiString;

    public UIStringTranslationEntity(UIStringEntity uiStringId, LanguageEntity language, String uiString) {
        this.uiStringId = uiStringId;
        this.language = language;
        this.uiString = uiString;
    }

    public UIStringTranslationEntity(){}

    public UIStringEntity getUiStringId() {
        return uiStringId;
    }

    public void setUiStringId(UIStringEntity uiStringId) {
        this.uiStringId = uiStringId;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity iso639Code) {
        this.language = iso639Code;
    }

    public String getUiString() {
        return uiString;
    }

    public void setUiString(String uiString) {
        this.uiString = uiString;
    }

    @Override
    public String toString() {
        return "UIStringTranslationEntity{" +
                "uiStringId=" + uiStringId +
                ", language='" + language + '\'' +
                ", uiString='" + uiString + '\'' +
                '}';
    }
}
