package com.example.auth.entity.plants;

import javax.persistence.*;

@Entity
@Table(name = "my_plants")
public class MyPlants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plant_name", nullable = false)
    private String plantName;

    @Column(name = "critical_lower_temperature", nullable = false)
    private Double criticalLowerTemperature;

    @Column(name = "recommended_temperature", nullable = false)
    private Double recommendedTemperature;

    @Column(name = "critical_higher_temperature", nullable = false)
    private Double criticalHigherTemperature;

    public MyPlants() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Double getCriticalLowerTemperature() {
        return criticalLowerTemperature;
    }

    public void setCriticalLowerTemperature(Double criticalLowerTemperature) {
        this.criticalLowerTemperature = criticalLowerTemperature;
    }

    public Double getCriticalHigherTemperature() {
        return criticalHigherTemperature;
    }

    public void setCriticalHigherTemperature(Double criticalHigherTemperature) {
        this.criticalHigherTemperature = criticalHigherTemperature;
    }

    public Double getRecommendedTemperature() {
        return recommendedTemperature;
    }

    public void setRecommendedTemperature(Double recommendedTemperature) {
        this.recommendedTemperature = recommendedTemperature;
    }

    @Override
    public String toString() {
        return plantName + " : " + recommendedTemperature + " .";
    }
}
