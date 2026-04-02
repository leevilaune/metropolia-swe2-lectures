package dev.onesnzeroes.fuelcalculator.db.entity.uistring;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="uistring")
public class UIStringEntity {

    @Id
    @Column(name ="id")
    private String id;
    @OneToMany(mappedBy = "uiStringId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UIStringTranslationEntity> translations;

    public UIStringEntity(String id) {
        this.id = id;
    }

    public UIStringEntity(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<UIStringTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<UIStringTranslationEntity> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "UIStringEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
