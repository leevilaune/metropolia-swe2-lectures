package dev.onesnzeroes.fuelcalculator.db.entity.result;

import dev.onesnzeroes.fuelcalculator.db.entity.LanguageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "result")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "consumption")
    private Double consumption;
    @Column(name = "price")
    private Double price;
    @Column(name = "total_fuel")
    private Double totalFuel;
    @Column(name = "total_cost")
    private Double totalCost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language", nullable = false)
    private LanguageEntity language;

    public ResultEntity(Double distance, Double consumption, Double price, Double totalFuel, Double totalCost, LanguageEntity language) {
        this.distance = distance;
        this.consumption = consumption;
        this.price = price;
        this.totalFuel = totalFuel;
        this.totalCost = totalCost;
        this.language = language;
    }

    public ResultEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(Double totalFuel) {
        this.totalFuel = totalFuel;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }
}
