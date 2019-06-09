package com.example.auth.entity;

import javax.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plantName;

    private Integer num;

    private Double recommendedTemperature;

    private Double recommendedHumidity;

    @ManyToOne
    @JoinColumn(name = "gb_id")
    private GrowBox responsibleGrowBox;

    public Plant() {
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

    public GrowBox getResponsibleGrowBox() {
        return responsibleGrowBox;
    }

    public void setResponsibleGrowBox(GrowBox responsibleGrowBox) {
        this.responsibleGrowBox = responsibleGrowBox;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getRecommendedTemperature() {
        return recommendedTemperature;
    }

    public void setRecommendedTemperature(Double recommendedTemperature) {
        this.recommendedTemperature = recommendedTemperature;
    }

    public Double getRecommendedHumidity() {
        return recommendedHumidity;
    }

    public void setRecommendedHumidity(Double recommendedHumidity) {
        this.recommendedHumidity = recommendedHumidity;
    }
}
