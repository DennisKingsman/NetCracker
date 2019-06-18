package com.example.auth.entity.plants;

public class PlantForm {

    private Long id;

    private String plantName;

    private Integer num;

    private Double criticalLowerTemperature;

    private Double recommendedTemperature;

    private Double criticalHigherTemperature;

    public PlantForm() {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getCriticalLowerTemperature() {
        return criticalLowerTemperature;
    }

    public void setCriticalLowerTemperature(Double criticalLowerTemperature) {
        this.criticalLowerTemperature = criticalLowerTemperature;
    }

    public Double getRecommendedTemperature() {
        return recommendedTemperature;
    }

    public void setRecommendedTemperature(Double recommendedTemperature) {
        this.recommendedTemperature = recommendedTemperature;
    }

    public Double getCriticalHigherTemperature() {
        return criticalHigherTemperature;
    }

    public void setCriticalHigherTemperature(Double criticalHigherTemperature) {
        this.criticalHigherTemperature = criticalHigherTemperature;
    }
}
