package dev.onesnzeroes.fuelcalculator.db.entity.uistring;

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
    @Column(name="iso639code")
    private String iso639Code;
    @Column(name="string")
    private String uiString;

    public UIStringTranslationEntity(UIStringEntity uiStringId, String iso639Code, String uiString) {
        this.uiStringId = uiStringId;
        this.iso639Code = iso639Code;
        this.uiString = uiString;
    }

    public UIStringTranslationEntity(){}

    public UIStringEntity getUiStringId() {
        return uiStringId;
    }

    public void setUiStringId(UIStringEntity uiStringId) {
        this.uiStringId = uiStringId;
    }

    public String getIso639Code() {
        return iso639Code;
    }

    public void setIso639Code(String iso639Code) {
        this.iso639Code = iso639Code;
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
                ", iso639Code='" + iso639Code + '\'' +
                ", uiString='" + uiString + '\'' +
                '}';
    }
}
